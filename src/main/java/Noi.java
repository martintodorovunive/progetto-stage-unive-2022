import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.Quorum;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

// I VALORI NELLE VARIABILI GLOBALI E GENERALI SONO DA SOSTITUIRE CON QUELLI DEL PROPRIO NETWORK, E DATABASE

public class Noi {

    //da aggiornare ogni qualvolta si pusha factory
    private static final String FACTORY_SC_ADDRESS = "0x4082298cb3b252d46bf1b4445269df37603daf6c";
    private static final String RPC_ENDPOINT = "http://127.0.0.1:20000";
    private static final String PRIVATE_URL = "";
    private static final String TESSERA_PUBLIC_KEY = "";
    private static final String PRIVATE_KEY = "b9a4bd1539c15bcc83fa9078fe89200b6e9e802ae992f13cd83c853f16e8bed4";

    public static void main(String[] args) throws Exception {

        //connessione al database
        Connection connection = null;
        String jdbcURL = "jdbc:postgresql://localhost:5432/nomedatabase";
        String username = "postgres";
        String password = "password";
        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connessione al database avvenuta con successo!");
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
        System.out.println("Member1 address: " + credentials.getAddress());

        TransactionManager transactionManager = new RawTransactionManager(web3, credentials, 1337);
        StaticGasProvider gasProvider = new StaticGasProvider(BigInteger.valueOf(0), BigInteger.valueOf(8000000));

        System.out.println("----------------------------------------\nChe cosa vuoi fare?\n(1) Deploy di Factory\n(2) Assegnare un nuovo ruolo\n(3) Altro");
        System.out.print("-> ");
        Scanner in = new Scanner(System.in);
        int scelta = in.nextInt();
        switch (scelta) {
            case 1: deployFactory(web3, transactionManager, gasProvider); break;
            case 2: assegnaRuolo(web3, transactionManager, gasProvider); break;
            default: System.out.println("Non c'è altro da fare...");
        }

    }

    public static void deployFactory(Quorum web3, TransactionManager transactionManager, StaticGasProvider gasProvider) throws Exception {
        System.out.println("----------------------------------------\nDeploy di factory in corso...");
        String factory = Factory.deploy(web3, transactionManager, gasProvider).send().getContractAddress();
        System.out.println("Deploy eseguito all'indirizzo " + factory + "\n----------------------------------------");
    }

    public static void assegnaRuolo(Quorum web3, TransactionManager transactionManager, StaticGasProvider gasProvider) throws Exception {
        System.out.println("----------------------------------------\n"+ FACTORY_SC_ADDRESS +"\nInserire l'address a cui assegnare un nuovo ruolo:");
        System.out.print("-> ");
        Scanner in1 = new Scanner(System.in);
        String addressNuovoRuolo = in1.nextLine();
        System.out.println("Scegliere il ruolo da asegnare:\n(1) Recuperatore\n(2) Creditore");
        System.out.print("-> ");
        Scanner in2 = new Scanner(System.in);
        int scelta = in2.nextInt();
        Factory factory = Factory.load(FACTORY_SC_ADDRESS, web3, transactionManager, gasProvider);
        switch (scelta) {
            case 1: System.out.println("Assegnazione ruolo di recuperatore ad account " + addressNuovoRuolo + "...");
                    factory.nuovoRuolo(addressNuovoRuolo, BigInteger.valueOf(scelta)).send();
                    System.out.println(factory.recuperatori(BigInteger.valueOf(0)).send());
                    System.out.println("Fatto!");
                    break;
            case 2: System.out.println("Assegnazione ruolo di creditore ad account" + addressNuovoRuolo + "...");
                    factory.nuovoRuolo(addressNuovoRuolo, BigInteger.valueOf(scelta));
                    System.out.println("Fatto!");
                    break;
            default: System.out.println("Errore, valore errato inserito");
        }
        System.out.println("----------------------------------------");
    }

    public static void rimuoviRuolo(Quorum web3, Credentials credentials, StaticGasProvider gasProvider) throws Exception {
        System.out.println("----------------------------------------\nInserire l'address a cui rimuovere il ruolo:");
        System.out.print("-> ");
        Scanner in1 = new Scanner(System.in);
        String addressRuolo = in1.nextLine();
        System.out.println("Scegliere il ruolo da rimuovere:\n(1) Recuperatore\n(2) Creditore");
        System.out.print("-> ");
        Scanner in2 = new Scanner(System.in);
        int scelta = in2.nextInt();
        Factory factory = Factory.load(FACTORY_SC_ADDRESS, web3, credentials, gasProvider);
        switch (scelta) {
            case 1: System.out.println("Rimozione ruolo di recuperatore da account " + addressRuolo + "...");
                    //todo controllo che prima abbia effettivamente quel ruolo
                    byte[] ruolo1 = factory.RECUPERATORE().send();
                    factory.revokeRole(ruolo1, addressRuolo);
                    System.out.println("Fatto!");
                    break;
            case 2: System.out.println("Rimozione ruolo di creditore da account" + addressRuolo + "...");
                    //todo controllo che prima abbia effettivamente quel ruolo
                    byte[] ruolo2 = factory.DEBITORE().send();
                    factory.revokeRole(ruolo2, addressRuolo);
                    System.out.println("Fatto!");
            default: System.out.println("Errore, valore errato inserito");
        }
        System.out.println("----------------------------------------");
    }

}
