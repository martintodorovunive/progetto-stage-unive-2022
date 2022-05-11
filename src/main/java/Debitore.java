import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.Quorum;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;
import java.math.BigInteger;
import java.util.Scanner;

// I VALORI NELLE VARIABILI GLOBALI E GENERALI SONO DA SOSTITUIRE CON QUELLI DEL PROPRIO NETWORK, E DATABASE

public class Debitore {

    private static final String RPC_ENDPOINT = "http://127.0.0.1:20004";
    private static final String PRIVATE_URL = "";
    private static final String TESSERA_PUBLIC_KEY = "";
    private static final String PRIVATE_KEY = "4107f0b6bf67a3bc679a15fe36f640415cf4da6a4820affaac89c8b280dfd1b3";

    public static void main(String[] args) throws Exception {

        //connessione al nodo di appartenenza
        Quorum web3 = Quorum.build(new HttpService(RPC_ENDPOINT));
        Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        System.out.println(clientVersion+"\n");

        Credentials credentials = Credentials.create(PRIVATE_KEY);
        System.out.println("Member3 address: " + credentials.getAddress());

        TransactionManager transactionManager = new RawTransactionManager(web3, credentials, 1337);
        StaticGasProvider gasProvider = new StaticGasProvider(BigInteger.valueOf(0), BigInteger.valueOf(8000000));

        System.out.println("----------------------------------------\nInserire input pratica address:");
        System.out.print("->");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        getPratica(web3, transactionManager, gasProvider, input);

    }

    public static void getPratica(Quorum web3, TransactionManager transactionManager, StaticGasProvider gasProvider, String addressPratica) throws Exception {
        Pratica pratica = Pratica.load(addressPratica, web3, transactionManager, gasProvider);
        System.out.println("----------------------------------------\n" +
                "id pratica: " + pratica.idPratica().send() +
                "\ncausa:" + pratica.causa().send() +
                "\nimporto da pagare:" + pratica.daPagare().send() +
                "\nimporto pagato:" + pratica.pagato().send() +
                "\nstatus pratica:" + pratica.status().send() +
                "\n----------------------------------------");
    }

}
