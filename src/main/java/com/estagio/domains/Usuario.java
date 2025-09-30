package com.estagio.domains;

import com.estagio.domains.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

    @Entity
    @Table(name = "usuario")
    @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
    public abstract class Usuario {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
        @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
        protected Long id;
        protected String firstName;
        protected String lastName;

        @Column(unique = true)
        protected String cpf;

        @Column(unique = true)
        protected String email;
        protected String password;

        @JsonFormat(pattern = "dd/MM/yyyy")
        protected LocalDate createdAt = LocalDate.now();

        @Enumerated(EnumType.STRING)
        @JoinColumn(name = "tipoUsuario")
        private TipoUsuario tipoUsuario;

        public Usuario() {

        }

        public Usuario(Long id, String firstName, String lastName, String cpf, String email, String password, TipoUsuario tipoUsuario) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.cpf = cpf;
            this.email = email;
            this.password = password;
            this.tipoUsuario = tipoUsuario;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public LocalDate getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDate createdAt) {
            this.createdAt = createdAt;
        }

        public TipoUsuario getTipoUsuario() {
            return tipoUsuario;
        }

        public void setTipoUsuario(TipoUsuario tipoUsuario) {
            this.tipoUsuario = tipoUsuario;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Usuario usuario = (Usuario) o;
            return Objects.equals(id, usuario.id) && Objects.equals(cpf, usuario.cpf);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, cpf);
        }
    }

