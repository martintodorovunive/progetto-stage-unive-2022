// SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.7.0 <0.9.0;
import "openzeppelin-solidity/contracts/access/Ownable.sol";
import "openzeppelin-solidity/contracts/access/AccessControl.sol";

contract Pratica is Ownable, AccessControl {

    enum Gender{M,F,MISTERO}

    struct Persona {
        string nome;
        string cognome;
        bytes16 codiceFiscale;
        Gender genere;
        string dataNascita;
        string residenza;
        string domicilio;
        string mail;
        string telefono;
    }

    int public idPratica;
    string public causa;
    string public status;
    uint public pagato;
    uint public daPagare;
    //formato epoch
    uint public dataScadenza;

    Persona public debitore;
    Persona public recuperatore;
    address public ownerPratica;

    modifier validityCheck {
        if((block.timestamp < dataScadenza)&&(daPagare > 0)){
            _;
        }
        else{
            status = "PRATICA CHIUSA";
        }
    }

    constructor(address _ownerPratica) {
        ownerPratica = _ownerPratica;
        _transferOwnership(ownerPratica);
    }

    function aggiornaPratica(int _idPratica, string memory _causa, string memory _status, uint _pagato, uint _daPagare, uint _dataScadenza) public onlyOwner validityCheck {
        idPratica = _idPratica;
        causa = _causa;
        status = _status;
        pagato = _pagato;
        daPagare = _daPagare;
        dataScadenza = _dataScadenza;
    }

    // Ho deciso di mettere il debitore come setter a parte nel caso poi in futuro si volesse cambiare il responsabile del pagamento
    function aggiornaDebitore(
        string memory _nome,
        string memory _cognome,
        bytes16 _codiceFiscale,
        int _genere, // 1 == MAN, 2 == WOMAN
        string memory _dataNascita,
        string memory _residenza,
        string memory _domicilio,
        string memory _mail,
        string memory _telefono) internal onlyOwner validityCheck {

        Gender genereScelto;

        if (_genere == 1)
            genereScelto = Gender.M;
        else if (_genere == 2)
            genereScelto = Gender.F;
        else
            genereScelto = Gender.MISTERO;

        debitore = Persona(_nome, _cognome, _codiceFiscale, genereScelto, _dataNascita, _residenza, _domicilio, _mail, _telefono);
    }

    function aggiornaRecuperatore(
        string memory _nome,
        string memory _cognome,
        string memory _mail) internal onlyOwner validityCheck {

        recuperatore = Persona(_nome, _cognome, "", Gender.MISTERO, "", "", "", _mail, "");
    }

    function aggiornaDebito(uint pagamento) public onlyOwner {
        pagato += pagamento;
        daPagare -= pagamento;
    }

    function aggiornaStatus(string memory _status) public onlyOwner {
        status = _status;
    }

}


contract Factory is Ownable, AccessControl {

    bytes32 public constant RECUPERATORE = keccak256("RECUPERATORE");
    bytes32 public constant DEBITORE = keccak256("DEBITORE");

    Pratica[] public pratiche;
    address[] public recuperatori;
    address[] public indebitati;

    int public index = 0;

    constructor() {
        _setupRole(DEFAULT_ADMIN_ROLE, msg.sender);
    }

    function nuovoRuolo(address addr, int ruolo) public onlyOwner {
        // ruolo 1 == recuperatore, ruolo 2 == debitore
        if(ruolo == 1) {
            _grantRole(RECUPERATORE, addr);
            recuperatori.push(addr);
        }
        else if(ruolo == 2) {
            _grantRole(DEBITORE, addr);
            indebitati.push(addr);
        }
    }

    function nuovaPratica() public onlyRole(RECUPERATORE) {
        Pratica pratica = new Pratica(msg.sender);
        pratiche.push(pratica);
        index += 1;
    }

}