import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class Factory extends Contract {
    public static final String BINARY = "6080604052600060055534801561001557600080fd5b5061001f3361002f565b61002a60003361007f565b610114565b600080546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b610089828261008d565b5050565b60008281526001602090815260408083206001600160a01b038516845290915290205460ff166100895760008281526001602081815260408084206001600160a01b0386168086529252808420805460ff19169093179092559051339285917f2f8788117e7eff1d82e926ec794901d17c78024a50270940304540a733656f0d9190a45050565b6121e3806101236000396000f3fe608060405234801561001057600080fd5b50600436106101165760003560e01c8063715018a6116100a2578063a217fddf11610071578063a217fddf1461023e578063d547741f14610246578063dbb3072414610259578063dfa7075f14610280578063f2fde38b146102a757600080fd5b8063715018a6146101ff5780638da5cb5b1461020757806391d148541461021857806393bb28601461022b57600080fd5b806336568abe116100e957806336568abe146101935780633e83823b146101a657806344d34e9e146101ae57806357ccc7da146101d957806370d0082e146101ec57600080fd5b806301ffc9a71461011b578063248a9ca3146101435780632986c0e5146101755780632f2ff15d1461017e575b600080fd5b61012e610129366004610a47565b6102ba565b60405190151581526020015b60405180910390f35b610167610151366004610a71565b6000908152600160208190526040909120015490565b60405190815260200161013a565b61016760055481565b61019161018c366004610aa6565b6102f1565b005b6101916101a1366004610aa6565b61031d565b6101916103a0565b6101c16101bc366004610a71565b610474565b6040516001600160a01b03909116815260200161013a565b6101916101e7366004610ad2565b61049e565b6101c16101fa366004610a71565b6105ce565b6101916105de565b6000546001600160a01b03166101c1565b61012e610226366004610aa6565b610614565b6101c1610239366004610a71565b61063f565b610167600081565b610191610254366004610aa6565b61064f565b6101677fae6c82bfcbfde5a5b0533ed0f46d87b7bff1a9a1e62221a5f4fad5c7b28e0d2d81565b6101677f05b72d759401cabc31eac5797257eb6abed6ef279ddf5aded8bb950f06f7b0bd81565b6101916102b5366004610afc565b610676565b60006001600160e01b03198216637965db0b60e01b14806102eb57506301ffc9a760e01b6001600160e01b03198316145b92915050565b6000828152600160208190526040909120015461030e8133610711565b6103188383610775565b505050565b6001600160a01b03811633146103925760405162461bcd60e51b815260206004820152602f60248201527f416363657373436f6e74726f6c3a2063616e206f6e6c792072656e6f756e636560448201526e103937b632b9903337b91039b2b63360891b60648201526084015b60405180910390fd5b61039c82826107e0565b5050565b7fae6c82bfcbfde5a5b0533ed0f46d87b7bff1a9a1e62221a5f4fad5c7b28e0d2d6103cb8133610711565b6000336040516103da90610a3a565b6001600160a01b039091168152602001604051809103906000f080158015610406573d6000803e3d6000fd5b50600280546001808201835560009283527f405787fa12a823e0f2b7631cc41b3ba8828b3321ca811111fa75cd3aa3bb5ace90910180546001600160a01b0319166001600160a01b03851617905560058054939450909290919061046b908490610b2d565b90915550505050565b6002818154811061048457600080fd5b6000918252602090912001546001600160a01b0316905081565b6000546001600160a01b031633146104c85760405162461bcd60e51b815260040161038990610b6e565b8060010361054b576104fa7fae6c82bfcbfde5a5b0533ed0f46d87b7bff1a9a1e62221a5f4fad5c7b28e0d2d83610775565b600380546001810182556000919091527fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b0180546001600160a01b0384166001600160a01b03199091161790555050565b8060020361039c5761057d7f05b72d759401cabc31eac5797257eb6abed6ef279ddf5aded8bb950f06f7b0bd83610775565b600480546001810182556000919091527f8a35acfbc15ff81a39ae7d344fd709f28e8600b4aa8c65c6b64bfe7fe36bd19b0180546001600160a01b0384166001600160a01b03199091161790555050565b6004818154811061048457600080fd5b6000546001600160a01b031633146106085760405162461bcd60e51b815260040161038990610b6e565b6106126000610847565b565b60009182526001602090815260408084206001600160a01b0393909316845291905290205460ff1690565b6003818154811061048457600080fd5b6000828152600160208190526040909120015461066c8133610711565b61031883836107e0565b6000546001600160a01b031633146106a05760405162461bcd60e51b815260040161038990610b6e565b6001600160a01b0381166107055760405162461bcd60e51b815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201526564647265737360d01b6064820152608401610389565b61070e81610847565b50565b61071b8282610614565b61039c57610733816001600160a01b03166014610897565b61073e836020610897565b60405160200161074f929190610bd3565b60408051601f198184030181529082905262461bcd60e51b825261038991600401610c48565b61077f8282610614565b61039c5760008281526001602081815260408084206001600160a01b0386168086529252808420805460ff19169093179092559051339285917f2f8788117e7eff1d82e926ec794901d17c78024a50270940304540a733656f0d9190a45050565b6107ea8282610614565b1561039c5760008281526001602090815260408083206001600160a01b0385168085529252808320805460ff1916905551339285917ff6391f5c32d9c69d2a47ea670b442974b53935d1edc7fd64eb21e047a839171b9190a45050565b600080546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b606060006108a6836002610c7b565b6108b1906002610c9a565b67ffffffffffffffff8111156108c9576108c9610cb2565b6040519080825280601f01601f1916602001820160405280156108f3576020820181803683370190505b509050600360fc1b8160008151811061090e5761090e610cc8565b60200101906001600160f81b031916908160001a905350600f60fb1b8160018151811061093d5761093d610cc8565b60200101906001600160f81b031916908160001a9053506000610961846002610c7b565b61096c906001610c9a565b90505b60018111156109e4576f181899199a1a9b1b9c1cb0b131b232b360811b85600f16601081106109a0576109a0610cc8565b1a60f81b8282815181106109b6576109b6610cc8565b60200101906001600160f81b031916908160001a90535060049490941c936109dd81610cde565b905061096f565b508315610a335760405162461bcd60e51b815260206004820181905260248201527f537472696e67733a20686578206c656e67746820696e73756666696369656e746044820152606401610389565b9392505050565b6114b880610cf683390190565b600060208284031215610a5957600080fd5b81356001600160e01b031981168114610a3357600080fd5b600060208284031215610a8357600080fd5b5035919050565b80356001600160a01b0381168114610aa157600080fd5b919050565b60008060408385031215610ab957600080fd5b82359150610ac960208401610a8a565b90509250929050565b60008060408385031215610ae557600080fd5b610aee83610a8a565b946020939093013593505050565b600060208284031215610b0e57600080fd5b610a3382610a8a565b634e487b7160e01b600052601160045260246000fd5b600080821280156001600160ff1b0384900385131615610b4f57610b4f610b17565b600160ff1b8390038412811615610b6857610b68610b17565b50500190565b6020808252818101527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604082015260600190565b60005b83811015610bbe578181015183820152602001610ba6565b83811115610bcd576000848401525b50505050565b7f416363657373436f6e74726f6c3a206163636f756e7420000000000000000000815260008351610c0b816017850160208801610ba3565b7001034b99036b4b9b9b4b733903937b6329607d1b6017918401918201528351610c3c816028840160208801610ba3565b01602801949350505050565b6020815260008251806020840152610c67816040850160208701610ba3565b601f01601f19169190910160400192915050565b6000816000190483118215151615610c9557610c95610b17565b500290565b60008219821115610cad57610cad610b17565b500190565b634e487b7160e01b600052604160045260246000fd5b634e487b7160e01b600052603260045260246000fd5b600081610ced57610ced610b17565b50600019019056fe608060405234801561001057600080fd5b506040516114b83803806114b883398101604081905261002f916100b5565b61003833610065565b601880546001600160a01b0319166001600160a01b03831690811790915561005f90610065565b506100e5565b600080546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b6000602082840312156100c757600080fd5b81516001600160a01b03811681146100de57600080fd5b9392505050565b6113c4806100f46000396000f3fe608060405234801561001057600080fd5b50600436106101425760003560e01c80638da5cb5b116100b8578063cc0df9d31161007c578063cc0df9d3146102a4578063d547741f146102ac578063dae2fc0d146102bf578063e5461c7a146102c8578063ea1aa5e9146102d1578063f2fde38b146102da57600080fd5b80638da5cb5b1461025d57806391d148541461026e578063a217fddf14610281578063ac514f8b14610289578063c42e97741461029157600080fd5b80632f2ff15d1161010a5780632f2ff15d146101f657806336568abe146102095780633e89d2d11461021c57806363184c901461023957806368f889d614610242578063715018a61461025557600080fd5b806301ffc9a7146101475780630b1d55381461016f5780630cfd2b351461019a578063200d2ed2146101af578063248a9ca3146101c4575b600080fd5b61015a610155366004610ec1565b6102ed565b60405190151581526020015b60405180910390f35b601854610182906001600160a01b031681565b6040516001600160a01b039091168152602001610166565b6101ad6101a8366004610f8e565b610324565b005b6101b761036e565b6040516101669190611027565b6101e86101d236600461103a565b6000908152600160208190526040909120015490565b604051908152602001610166565b6101ad61020436600461106f565b6103fc565b6101ad61021736600461106f565b610428565b6102246104a2565b6040516101669998979695949392919061109b565b6101e860055481565b6101ad610250366004611175565b6108a1565b6101ad610963565b6000546001600160a01b0316610182565b61015a61027c36600461106f565b610999565b6101e8600081565b6102246109c4565b6101ad61029f36600461103a565b6109d3565b6101b7610a30565b6101ad6102ba36600461106f565b610a3d565b6101e860065481565b6101e860075481565b6101e860025481565b6101ad6102e83660046111fe565b610a64565b60006001600160e01b03198216637965db0b60e01b148061031e57506301ffc9a760e01b6001600160e01b03198316145b92915050565b6000546001600160a01b031633146103575760405162461bcd60e51b815260040161034e90611219565b60405180910390fd5b805161036a906004906020840190610e28565b5050565b6004805461037b9061124e565b80601f01602080910402602001604051908101604052809291908181526020018280546103a79061124e565b80156103f45780601f106103c9576101008083540402835291602001916103f4565b820191906000526020600020905b8154815290600101906020018083116103d757829003601f168201915b505050505081565b600082815260016020819052604090912001546104198133610aff565b6104238383610b63565b505050565b6001600160a01b03811633146104985760405162461bcd60e51b815260206004820152602f60248201527f416363657373436f6e74726f6c3a2063616e206f6e6c792072656e6f756e636560448201526e103937b632b9903337b91039b2b63360891b606482015260840161034e565b61036a8282610bce565b6008805481906104b19061124e565b80601f01602080910402602001604051908101604052809291908181526020018280546104dd9061124e565b801561052a5780601f106104ff5761010080835404028352916020019161052a565b820191906000526020600020905b81548152906001019060200180831161050d57829003601f168201915b50505050509080600101805461053f9061124e565b80601f016020809104026020016040519081016040528092919081815260200182805461056b9061124e565b80156105b85780601f1061058d576101008083540402835291602001916105b8565b820191906000526020600020905b81548152906001019060200180831161059b57829003601f168201915b5050505060028301546003840180549394608083901b94600160801b90930460ff169350916105e69061124e565b80601f01602080910402602001604051908101604052809291908181526020018280546106129061124e565b801561065f5780601f106106345761010080835404028352916020019161065f565b820191906000526020600020905b81548152906001019060200180831161064257829003601f168201915b5050505050908060040180546106749061124e565b80601f01602080910402602001604051908101604052809291908181526020018280546106a09061124e565b80156106ed5780601f106106c2576101008083540402835291602001916106ed565b820191906000526020600020905b8154815290600101906020018083116106d057829003601f168201915b5050505050908060050180546107029061124e565b80601f016020809104026020016040519081016040528092919081815260200182805461072e9061124e565b801561077b5780601f106107505761010080835404028352916020019161077b565b820191906000526020600020905b81548152906001019060200180831161075e57829003601f168201915b5050505050908060060180546107909061124e565b80601f01602080910402602001604051908101604052809291908181526020018280546107bc9061124e565b80156108095780601f106107de57610100808354040283529160200191610809565b820191906000526020600020905b8154815290600101906020018083116107ec57829003601f168201915b50505050509080600701805461081e9061124e565b80601f016020809104026020016040519081016040528092919081815260200182805461084a9061124e565b80156108975780601f1061086c57610100808354040283529160200191610897565b820191906000526020600020905b81548152906001019060200180831161087a57829003601f168201915b5050505050905089565b6000546001600160a01b031633146108cb5760405162461bcd60e51b815260040161034e90611219565b600754421080156108de57506000600654115b1561092457600286905584516108fb906003906020880190610e28565b50835161090f906004906020870190610e28565b5060058390556006829055600781905561095b565b60408051808201909152600e8082526d505241544943412043484955534160901b602090920191825261095991600491610e28565b505b505050505050565b6000546001600160a01b0316331461098d5760405162461bcd60e51b815260040161034e90611219565b6109976000610c35565b565b60009182526001602090815260408084206001600160a01b0393909316845291905290205460ff1690565b6010805481906104b19061124e565b6000546001600160a01b031633146109fd5760405162461bcd60e51b815260040161034e90611219565b8060056000828254610a0f919061129e565b925050819055508060066000828254610a2891906112b6565b909155505050565b6003805461037b9061124e565b60008281526001602081905260409091200154610a5a8133610aff565b6104238383610bce565b6000546001600160a01b03163314610a8e5760405162461bcd60e51b815260040161034e90611219565b6001600160a01b038116610af35760405162461bcd60e51b815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201526564647265737360d01b606482015260840161034e565b610afc81610c35565b50565b610b098282610999565b61036a57610b21816001600160a01b03166014610c85565b610b2c836020610c85565b604051602001610b3d9291906112cd565b60408051601f198184030181529082905262461bcd60e51b825261034e91600401611027565b610b6d8282610999565b61036a5760008281526001602081815260408084206001600160a01b0386168086529252808420805460ff19169093179092559051339285917f2f8788117e7eff1d82e926ec794901d17c78024a50270940304540a733656f0d9190a45050565b610bd88282610999565b1561036a5760008281526001602090815260408083206001600160a01b0385168085529252808320805460ff1916905551339285917ff6391f5c32d9c69d2a47ea670b442974b53935d1edc7fd64eb21e047a839171b9190a45050565b600080546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b60606000610c94836002611342565b610c9f90600261129e565b67ffffffffffffffff811115610cb757610cb7610eeb565b6040519080825280601f01601f191660200182016040528015610ce1576020820181803683370190505b509050600360fc1b81600081518110610cfc57610cfc611361565b60200101906001600160f81b031916908160001a905350600f60fb1b81600181518110610d2b57610d2b611361565b60200101906001600160f81b031916908160001a9053506000610d4f846002611342565b610d5a90600161129e565b90505b6001811115610dd2576f181899199a1a9b1b9c1cb0b131b232b360811b85600f1660108110610d8e57610d8e611361565b1a60f81b828281518110610da457610da4611361565b60200101906001600160f81b031916908160001a90535060049490941c93610dcb81611377565b9050610d5d565b508315610e215760405162461bcd60e51b815260206004820181905260248201527f537472696e67733a20686578206c656e67746820696e73756666696369656e74604482015260640161034e565b9392505050565b828054610e349061124e565b90600052602060002090601f016020900481019282610e565760008555610e9c565b82601f10610e6f57805160ff1916838001178555610e9c565b82800160010185558215610e9c579182015b82811115610e9c578251825591602001919060010190610e81565b50610ea8929150610eac565b5090565b5b80821115610ea85760008155600101610ead565b600060208284031215610ed357600080fd5b81356001600160e01b031981168114610e2157600080fd5b634e487b7160e01b600052604160045260246000fd5b600082601f830112610f1257600080fd5b813567ffffffffffffffff80821115610f2d57610f2d610eeb565b604051601f8301601f19908116603f01168101908282118183101715610f5557610f55610eeb565b81604052838152866020858801011115610f6e57600080fd5b836020870160208301376000602085830101528094505050505092915050565b600060208284031215610fa057600080fd5b813567ffffffffffffffff811115610fb757600080fd5b610fc384828501610f01565b949350505050565b60005b83811015610fe6578181015183820152602001610fce565b83811115610ff5576000848401525b50505050565b60008151808452611013816020860160208601610fcb565b601f01601f19169290920160200192915050565b602081526000610e216020830184610ffb565b60006020828403121561104c57600080fd5b5035919050565b80356001600160a01b038116811461106a57600080fd5b919050565b6000806040838503121561108257600080fd5b8235915061109260208401611053565b90509250929050565b60006101208083526110af8184018d610ffb565b905082810360208401526110c3818c610ffb565b6fffffffffffffffffffffffffffffffff198b1660408501529050600389106110fc57634e487b7160e01b600052602160045260246000fd5b88606084015282810360808401526111148189610ffb565b905082810360a08401526111288188610ffb565b905082810360c084015261113c8187610ffb565b905082810360e08401526111508186610ffb565b90508281036101008401526111658185610ffb565b9c9b505050505050505050505050565b60008060008060008060c0878903121561118e57600080fd5b86359550602087013567ffffffffffffffff808211156111ad57600080fd5b6111b98a838b01610f01565b965060408901359150808211156111cf57600080fd5b506111dc89828a01610f01565b945050606087013592506080870135915060a087013590509295509295509295565b60006020828403121561121057600080fd5b610e2182611053565b6020808252818101527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604082015260600190565b600181811c9082168061126257607f821691505b60208210810361128257634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052601160045260246000fd5b600082198211156112b1576112b1611288565b500190565b6000828210156112c8576112c8611288565b500390565b7f416363657373436f6e74726f6c3a206163636f756e7420000000000000000000815260008351611305816017850160208801610fcb565b7001034b99036b4b9b9b4b733903937b6329607d1b6017918401918201528351611336816028840160208801610fcb565b01602801949350505050565b600081600019048311821515161561135c5761135c611288565b500290565b634e487b7160e01b600052603260045260246000fd5b60008161138657611386611288565b50600019019056fea2646970667358221220849ff678496ca6a7acf2bfd4a1330e52d06984035b55a91f5ecfec754b59894e64736f6c634300080d0033a2646970667358221220e831ce34ef1809d65d82c68db1f9a3a592c3dac9aba7e841ba126e8ff3b7a5eb64736f6c634300080d0033";

    public static final String FUNC_DEBITORE = "DEBITORE";

    public static final String FUNC_DEFAULT_ADMIN_ROLE = "DEFAULT_ADMIN_ROLE";

    public static final String FUNC_RECUPERATORE = "RECUPERATORE";

    public static final String FUNC_GETROLEADMIN = "getRoleAdmin";

    public static final String FUNC_GRANTROLE = "grantRole";

    public static final String FUNC_HASROLE = "hasRole";

    public static final String FUNC_INDEBITATI = "indebitati";

    public static final String FUNC_INDEX = "index";

    public static final String FUNC_NUOVAPRATICA = "nuovaPratica";

    public static final String FUNC_NUOVORUOLO = "nuovoRuolo";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PRATICHE = "pratiche";

    public static final String FUNC_RECUPERATORI = "recuperatori";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_RENOUNCEROLE = "renounceRole";

    public static final String FUNC_REVOKEROLE = "revokeRole";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event ROLEADMINCHANGED_EVENT = new Event("RoleAdminChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Bytes32>(true) {}, new TypeReference<Bytes32>(true) {}));
    ;

    public static final Event ROLEGRANTED_EVENT = new Event("RoleGranted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event ROLEREVOKED_EVENT = new Event("RoleRevoked", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected Factory(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Factory(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Factory(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Factory(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public List<RoleAdminChangedEventResponse> getRoleAdminChangedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ROLEADMINCHANGED_EVENT, transactionReceipt);
        ArrayList<RoleAdminChangedEventResponse> responses = new ArrayList<RoleAdminChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RoleAdminChangedEventResponse typedResponse = new RoleAdminChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.previousAdminRole = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.newAdminRole = (byte[]) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RoleAdminChangedEventResponse> roleAdminChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RoleAdminChangedEventResponse>() {
            @Override
            public RoleAdminChangedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ROLEADMINCHANGED_EVENT, log);
                RoleAdminChangedEventResponse typedResponse = new RoleAdminChangedEventResponse();
                typedResponse.log = log;
                typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.previousAdminRole = (byte[]) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.newAdminRole = (byte[]) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RoleAdminChangedEventResponse> roleAdminChangedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ROLEADMINCHANGED_EVENT));
        return roleAdminChangedEventFlowable(filter);
    }

    public List<RoleGrantedEventResponse> getRoleGrantedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ROLEGRANTED_EVENT, transactionReceipt);
        ArrayList<RoleGrantedEventResponse> responses = new ArrayList<RoleGrantedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RoleGrantedEventResponse typedResponse = new RoleGrantedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RoleGrantedEventResponse> roleGrantedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RoleGrantedEventResponse>() {
            @Override
            public RoleGrantedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ROLEGRANTED_EVENT, log);
                RoleGrantedEventResponse typedResponse = new RoleGrantedEventResponse();
                typedResponse.log = log;
                typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.sender = (String) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RoleGrantedEventResponse> roleGrantedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ROLEGRANTED_EVENT));
        return roleGrantedEventFlowable(filter);
    }

    public List<RoleRevokedEventResponse> getRoleRevokedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ROLEREVOKED_EVENT, transactionReceipt);
        ArrayList<RoleRevokedEventResponse> responses = new ArrayList<RoleRevokedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RoleRevokedEventResponse typedResponse = new RoleRevokedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RoleRevokedEventResponse> roleRevokedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RoleRevokedEventResponse>() {
            @Override
            public RoleRevokedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ROLEREVOKED_EVENT, log);
                RoleRevokedEventResponse typedResponse = new RoleRevokedEventResponse();
                typedResponse.log = log;
                typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.sender = (String) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RoleRevokedEventResponse> roleRevokedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ROLEREVOKED_EVENT));
        return roleRevokedEventFlowable(filter);
    }

    public RemoteFunctionCall<byte[]> DEBITORE() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DEBITORE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> DEFAULT_ADMIN_ROLE() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DEFAULT_ADMIN_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> RECUPERATORE() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_RECUPERATORE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> getRoleAdmin(byte[] role) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETROLEADMIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<TransactionReceipt> grantRole(byte[] role, String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_GRANTROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.web3j.abi.datatypes.Address(160, account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> hasRole(byte[] role, String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_HASROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> indebitati(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_INDEBITATI, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> index() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_INDEX, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> nuovaPratica() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_NUOVAPRATICA, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> nuovoRuolo(String addr, BigInteger ruolo) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_NUOVORUOLO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, addr), 
                new org.web3j.abi.datatypes.generated.Int256(ruolo)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> pratiche(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PRATICHE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> recuperatori(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_RECUPERATORI, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceRole(byte[] role, String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.web3j.abi.datatypes.Address(160, account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> revokeRole(byte[] role, String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REVOKEROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.web3j.abi.datatypes.Address(160, account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Factory load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Factory(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Factory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Factory(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Factory load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Factory(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Factory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Factory(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Factory> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Factory.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Factory> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Factory.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Factory> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Factory.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Factory> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Factory.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class RoleAdminChangedEventResponse extends BaseEventResponse {
        public byte[] role;

        public byte[] previousAdminRole;

        public byte[] newAdminRole;
    }

    public static class RoleGrantedEventResponse extends BaseEventResponse {
        public byte[] role;

        public String account;

        public String sender;
    }

    public static class RoleRevokedEventResponse extends BaseEventResponse {
        public byte[] role;

        public String account;

        public String sender;
    }
}
