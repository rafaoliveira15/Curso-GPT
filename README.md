<div align="center">

# Curso Chat GPT

> **Projeto educacional desenvolvido em Java abordando as atividades práticas do Curso de Chat GPT do SENAI.**  
> Cada atividade simula um desafio real de desenvolvimento, com foco em interfaces gráficas (Swing), validação de dados, persistência e POO.


---

[![Java](https://img.shields.io/badge/Java-11+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Swing](https://img.shields.io/badge/Java%20Swing-GUI-007396?style=for-the-badge&logo=java&logoColor=white)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![FlatLaf](https://img.shields.io/badge/FlatLaf-Modern%20UI-6C3483?style=for-the-badge)](https://www.formdev.com/flatlaf/)
[![Status](https://img.shields.io/badge/Status-Ativo-2ECC71?style=for-the-badge)]()

</div>

---

## Índice

[![Sobre](https://img.shields.io/badge/Sobre%20o%20Projeto-2E7D32?style=for-the-badge)](#sobre-o-projeto)
[![Tecnologias](https://img.shields.io/badge/Tecnologias-2E7D32?style=for-the-badge)](#tecnologias)
[![Atividades](https://img.shields.io/badge/Atividades-2E7D32?style=for-the-badge)](#atividades)
[![Prompts](https://img.shields.io/badge/Prompts-2E7D32?style=for-the-badge)](#prompts-das-atividades)
[![Funcionalidades](https://img.shields.io/badge/Funcionalidades-2E7D32?style=for-the-badge)](#funcionalidades)
[![Instalação](https://img.shields.io/badge/Instalação-2E7D32?style=for-the-badge)](#instalação)

---

## Sobre o Projeto

Este projeto foi criado para consolidar conhecimentos em:

- Interfaces desktop com Java
- Validação de dados sensíveis
- Integração com banco de dados
- Manipulação de arquivos
- Organização em camadas (modelo e visão)

---

## Tecnologias

- Java  
- Java Swing  
- JDBC (MySQL)  
- MySQL  
- FlatLaf  
- File I/O  
- Regex  
- POO  

---

## Atividades

### 1. Registro de Clientes com Banco de Dados  
Arquivo: `Atv1.java`  
Foco: Swing + JDBC + Validação de CPF  

---

### 2. Cadastro via Console  
Arquivo: `FormularioCliente.java`  
Foco: Scanner + POO  

---

### 3. Cadastro com JTable  
Arquivo: `CadastroClientes.java`  
Foco: JTable + FlatLaf  

---

### 4. Controle de Movimentação TXT  
Arquivo: `FormularioAppChallenge.java`  
Foco: File I/O  

---

### 5. Formulário com Máscaras  
Arquivo: `CadastroCliente.java`  
Foco: UX + Validação CPF  

---

## Prompts das Atividades

### Atividade 1 — Banco de Dados (Atv1.java)

> "Crie uma aplicação Java Swing para cadastro de clientes utilizando GridBagLayout. O formulário deve conter campos para Nome, E-mail, Telefone (com máscara) e CPF (com máscara). Implemente uma função robusta para validar o algoritmo do CPF (dígitos verificadores). Ao clicar em 'Salvar', o sistema deve validar o CPF, limpar caracteres não numéricos e persistir os dados em um banco de dados MySQL chamado sistema_clientes na tabela clientes (colunas: nome, email, telefone, cpf). Inclua um botão 'Limpar' para resetar o formulário e use JOptionPane para feedbacks."

---

### Atividade 2 — Console (FormularioCliente.java)

> "Desenvolva uma aplicação Java simples de console para cadastro de clientes. O programa deve pertencer ao pacote visao e interagir com uma classe Cliente no pacote modelo. Use a classe Scanner para ler o Nome e o E-mail do usuário. Implemente uma regra de validação que verifique se o e-mail contém o caractere '@'. Se for válido, instancie o objeto e exiba uma mensagem confirmando o salvamento (simulado via console); caso contrário, exiba uma mensagem de erro."

---

### Atividade 3 — JTable + FlatLaf (CadastroClientes.java)

> "Crie um programa em Java Swing para gerenciar um cadastro de clientes, utilizando a biblioteca FlatLaf (FlatLightLaf) para um visual moderno. A interface deve ser dividida em duas partes: um formulário no topo (GridLayout) e uma tabela (JTable) na parte inferior para listagem. Armazene os dados em um ArrayList de objetos Cliente. Campos necessários: Nome, Nascimento, CPF, Email e Celular (todos os campos de data e números devem ter máscaras). O sistema deve validar se o CPF é real e se nenhum campo está vazio antes de adicionar o cliente à lista e atualizar a tabela."

---

### Atividade 4 — Arquivo TXT (FormularioAppChallenge.java)

> "Desenvolva um 'Sistema de Controle de Movimentação - Versão Beta' em Java Swing. Utilize um GridLayout para organizar campos de: Data (com máscara), Operador (ComboBox), Fornecedor (ComboBox), Produto (ComboBox), Quantidade (JTextField) e Tipo de Movimentação (ComboBox: Entrada/Saída). Implemente a persistência em um arquivo chamado dados_movimentacao.txt. Importante: o arquivo não deve ser sobrescrito a cada salvamento (use o modo append). Adicione um botão 'Ver Registros' que leia o conteúdo do arquivo e o exiba em um JTextArea dentro de um JScrollPane em uma janela de diálogo."

---

## Funcionalidades

- Validação de CPF  
- Interfaces gráficas com Swing  
- Persistência em MySQL e TXT  
- Listagem dinâmica com JTable  
- Validação de formulários  
- Feedback ao usuário  

---

## Instalação

### Pré-requisitos

- Java JDK 11+
- MySQL
- IDE (IntelliJ, Eclipse ou VS Code)

---

### Banco de Dados

```sql
CREATE DATABASE sistema_clientes;

USE sistema_clientes;

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100),
    telefone VARCHAR(20),
    cpf VARCHAR(14)
);
```

---

<div align="center">

*Curso Chat GPT · SENAI · Atividade 01 · Sistema de Cadastro de Clientes*

</div>
