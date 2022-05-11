import okhttp3.OkHttpClient;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.Quorum;
import org.web3j.quorum.enclave.Enclave;
import org.web3j.quorum.enclave.Tessera;
import org.web3j.quorum.enclave.protocol.EnclaveService;
import org.web3j.quorum.tx.QuorumTransactionManager;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

// I VALORI NELLE VARIABILI GLOBALI E GENERALI SONO DA SOSTITUIRE CON QUELLI DEL PROPRIO NETWORK, E DATABASE

public class Recuperatore {

    private static final String FACTORY_SC_ADDRESS = "0x4082298cb3b252d46bf1b4445269df37603daf6c";
    private static final String RPC_ENDPOINT = "http://127.0.0.1:20002";
    private static final String TESSERA_URL = "http://127.0.0.1";
    private static final int TESSERA_PORT = 9082;
    private static final String TESSERA_PUBLIC_KEY = "QfeDAys9MPDs2XHExtc84jKGHxZg/aj52DTh0vtA3Xc=";
    private static final String PRIVATE_KEY = "f18166704e19b895c1e2698ebc82b4e007e6d2933f4b31be23662dd0ec602570";

    private static ArrayList<String> privateFor = new ArrayList<String>();;

    public static void main(String[] args) throws Exception {

        //connessione al database
        Connection connection = null;
        Statement statement = null;
        String jdbcURL = "jdbc:postgresql://localhost:5432/nomedatabase";
        String username = "postgres";
        String password = "password";
        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connessione al database avvenuta con successo!");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        //connessione al nodo di appartenenza
        Quorum web3 = Quorum.build(new HttpService(RPC_ENDPOINT));
        Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        System.out.println(clientVersion+"\n");

        Credentials credentials = Credentials.create(PRIVATE_KEY);
        System.out.println("Member2 address: " + credentials.getAddress());

        TransactionManager transactionManager = new RawTransactionManager(web3, credentials, 1337);
        StaticGasProvider gasProvider = new StaticGasProvider(BigInteger.valueOf(0), BigInteger.valueOf(8000000));
        EnclaveService enclaveService = new EnclaveService(TESSERA_URL, TESSERA_PORT, new OkHttpClient());
        Enclave enclave = new Tessera(enclaveService, web3);
        QuorumTransactionManager quorumTransactionManager = new QuorumTransactionManager(web3, credentials, TESSERA_PUBLIC_KEY, privateFor, enclave);

        System.out.println("----------------------------------------\nChe cosa vuoi fare?\n(1) Deploy di Pratica\n(2) Get Pratica\n(3) Altro");
        System.out.print("-> ");
        Scanner in = new Scanner(System.in);
        int scelta = in.nextInt();
        switch (scelta) {
            case 1: nuovaPratica(web3, credentials, transactionManager, quorumTransactionManager, gasProvider, connection, statement); break;
            case 2: getPratica(web3, transactionManager, gasProvider); break;
            default: System.out.println("Non c'è altro da fare...");
        }

    }

    public static void nuovaPratica(Quorum web3, Credentials credentials, TransactionManager transactionManager, QuorumTransactionManager quorumTransactionManager, StaticGasProvider gasProvider, Connection connection, Statement statement) throws Exception {
        System.out.println("----------------------------------------\nCreazione nuova pratica in corso...");

        Factory factory = Factory.load(FACTORY_SC_ADDRESS, web3, transactionManager, gasProvider);
        //crea la pratica, viene aggiornato il valore index
        factory.nuovaPratica().send();
        //restituisce l'ultimo valore index, necessario a recuperare l'indirizzo della pratica appena creata
        BigInteger index = factory.index().send();
        int aux = index.intValue() - 1;
        BigInteger indexArray = BigInteger.valueOf(aux);
        String addressPraticaCreata = factory.pratiche(indexArray).send();
        //inserimento delle informazioni della pratica nel db
        String sql = "INSERT INTO pratiche (index,address)" + " VALUES (" + (aux+1) + ", '" + addressPraticaCreata + "');";
        statement.executeUpdate(sql);
        System.out.println("Pratica numero " + aux + " creata con successo all'indirizzo " + addressPraticaCreata + "\n----------------------------------------");

        //parte 2: set dei valori della pratica, privato
        Pratica pratica = Pratica.load(addressPraticaCreata, web3, quorumTransactionManager, gasProvider);
        pratica.aggiornaPratica("Inserire qui i valori").send();

    }


    public void trasferisciPratica(Quorum web3, Credentials credentials, StaticGasProvider gasProvider, String newOnwer, String addressPratica) {
        System.out.println("----------------------------------------\nSei sicuro di volere trasferire la pratica a " + newOnwer + "? (Si/No)");
        Scanner in = new Scanner(System.in);
        String scelta = in.nextLine();
        boolean procedere = false;
        switch (scelta) {
            case "Si": procedere = true; break;
            case "si": procedere = true; break;
            default: ;
        }
        if(procedere){
            Pratica pratica = Pratica.load(addressPratica, web3, credentials, gasProvider);
            pratica.transferOwnership(newOnwer);
            System.out.println("Trasferimento avvenuto con successo\n----------------------------------------\n");
        }
        System.out.println("----------------------------------------\n");
    }

    public void chiudiPratica() {
        //viene fatto in maniera automatica all'interno dello smart contract
        //le funzioni dello smart contract infatti possono essere utilizzate solo se rispettano la condizione di validità
        //la condizione di validità si riferisce al requisito di operare entro la data di scadenza o che non si ancora saldato il debito
    }

    public void aggiornaDebito(int importoPagato, String addressPratica, Quorum web3, QuorumTransactionManager quorumTransactionManager, StaticGasProvider gasProvider) throws Exception {
        Pratica pratica = Pratica.load(addressPratica, web3, quorumTransactionManager, gasProvider);
        pratica.aggiornaDebito(BigInteger.valueOf(importoPagato)).send();
    }

    public void aggiornaDebitore(String addressPratica, Quorum web3, QuorumTransactionManager quorumTransactionManager, StaticGasProvider gasProvider) {
        Pratica pratica = Pratica.load(addressPratica, web3, quorumTransactionManager, gasProvider);
    }

    public void aggiornaStatusPratica(String status, String addressPratica, Quorum web3, QuorumTransactionManager quorumTransactionManager, StaticGasProvider gasProvider) throws Exception {
        Pratica pratica = Pratica.load(addressPratica, web3, quorumTransactionManager, gasProvider);
        pratica.aggiornaStatus(status).send();
    }

    public void condividiPratica(String tesseraToPubKey, String addressPratica, Quorum web3, QuorumTransactionManager quorumTransactionManager, StaticGasProvider gasProvider) throws Exception {
        privateFor.add(tesseraToPubKey);
        Pratica pratica = Pratica.load(addressPratica, web3, quorumTransactionManager, gasProvider);
        pratica.aggiornaPratica(
            pratica.idPratica().send(),
            pratica.causa().send(),
            pratica.status().send(),
            pratica.pagato().send(),
            pratica.daPagare().send(),
            pratica.dataScadenza().send()
        ).send();
    }

    public static void getPratica(Quorum web3, TransactionManager transactionManager, StaticGasProvider gasProvider) throws Exception {
        System.out.println("----------------------------------------\nInserire input pratica address:");
        System.out.print("->");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        Pratica pratica = Pratica.load(input, web3, transactionManager, gasProvider);
        System.out.println("----------------------------------------\nid pratica: " + pratica.idPratica().send() + "\nresto dei dati: ... \n----------------------------------------");
    }

    //funzione da usare nel seguente caso:
    //la data che troviamo nel database (si suppone stringa con formato sotto specificato) va converita in valore intero detto epoch prima di passarlo allo smart contract
    public static long formatTime(String data) {
        //stringa arriverà nel formato dd-MM-yyy e noi dobbiamo convertirla in yyyy-MM-dd HH:mm:ss.SSS, aggiungendo degli zeri nella sezione time
        String[] aux = data.split("-", 0);
        String dataFormattata = aux[2]+"-"+aux[1]+"-"+aux[0]+" 00:00:00.000";
        System.out.println(dataFormattata);
        ZonedDateTime zonedDateTime = LocalDateTime.parse(dataFormattata, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")).atZone(ZoneId.of("GMT"));
        return zonedDateTime.toInstant().toEpochMilli();
    }

}
