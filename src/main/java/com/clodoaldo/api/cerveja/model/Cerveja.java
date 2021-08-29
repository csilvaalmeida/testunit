package com.clodoaldo.api.cerveja.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cerveja {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private  long    iD;

        @Column(nullable = false, unique = true)
        private String  nome;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private  TipoCerveja tipo;

        @Column(nullable = false)
        private  int teorAlcool;

        @Column(nullable = false)
        private  int quantidade;

        @Column(nullable = false)
        private  int quantidadeMaxima;


      // gtters and setters
        public long getiD() {
            return iD;
        }

        public void setiD(long iD) {
            this.iD = iD;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public TipoCerveja getTipo() {
            return tipo;
        }

        public void setTipo(TipoCerveja tipo) {
            this.tipo = tipo;
        }

        public int getTeorAlcool() {
            return teorAlcool;
        }

        public void setTeorAlcool(int teorAlcool) {
            this.teorAlcool = teorAlcool;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
        }

        public int getQuantidadeMaxima() {
            return quantidadeMaxima;
        }

        public void setQuantidadeMaxima(int quantidadeMaxima) {
            this.quantidadeMaxima = quantidadeMaxima;
        }


        @Override
        public String toString() {
          return iD + " " +
                nome +" " +
                tipo + " " +
                teorAlcool + " " +
                quantidade + " " +
                quantidadeMaxima  ; }

};
