package com.estagio.services;

import com.estagio.domains.Cliente;
import com.estagio.domains.dtos.ClienteDTO;
import com.estagio.repositories.ClienteRepository;
import com.estagio.services.exceptions.DataIntegrityViolationException;
import com.estagio.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepo;

    public List<ClienteDTO> findAll(){
        return clienteRepo.findAll().stream()
                .map( obj -> new ClienteDTO(obj)).collect(Collectors.toList());
    }

    public Cliente findbyId(Long id){
        Optional<Cliente> obj = clienteRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:"+id));
    }

    public Cliente findbyCpf(String cpf){
        Optional<Cliente> obj = clienteRepo.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! CPF:"+cpf));
    }

    public Cliente findbyEmail(String email){
        Optional<Cliente> obj = clienteRepo.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Email:"+email));
    }

    public Cliente create(ClienteDTO objDto){
        objDto.setId(null);
        ValidaPorCPFeEmail(objDto);
        Cliente newObj = new Cliente(objDto);
        return clienteRepo.save(newObj);
    }

    public Cliente update(Long id, ClienteDTO objDto){
        objDto.setId(id);
        Cliente oldObj = findbyId(id);
        ValidaPorCPFeEmail(objDto);
        oldObj = new Cliente(objDto);
        return clienteRepo.save(oldObj);
    }

    public void delete(Long id){
        Cliente obj = findbyId(id);
        if(obj.getPedidos().size()>0){
            throw new DataIntegrityViolationException("Usuário não pode ser deletado pois possui Pedidos!");
        }
        clienteRepo.deleteById(id);
    }

    public void ValidaPorCPFeEmail (ClienteDTO objDto){
        Optional<Cliente> obj = clienteRepo.findByCpf(objDto.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = clienteRepo.findByEmail(objDto.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }
    }

}
