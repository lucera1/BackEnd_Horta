package com.estagio.domains;

import com.estagio.domains.dtos.ClienteDTO;
import com.estagio.domains.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Cliente extends Usuario{

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    @ManyToOne
    @Transient
    private Venda venda;

        public Cliente(Long id, String firstName, String lastName, String cpf, String email, String password, TipoUsuario tipoUsuario) {
            super(id, firstName, lastName, cpf, email, password, tipoUsuario);

        }


    public Cliente(ClienteDTO obj){
        this.id = obj.getId();
        this.firstName = obj.getFirstName();
        this.lastName = obj.getLastName();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.password = obj.getPassword();
        this.createdAt = obj.getCreatedAt();
        this.setTipoUsuario(TipoUsuario.CLIENTE);

        }


        public Cliente(){
            super();
        }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}


