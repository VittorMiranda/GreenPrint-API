-- ============================
-- TABELA: Usuario
-- ============================
CREATE TABLE usuario (
    id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    telefone VARCHAR(14),
    senha VARCHAR(50) NOT NULL,
    papel ENUM('CLIENTE', 'FUNCIONARIO', 'ADMINISTRADOR') NOT NULL DEFAULT 'CLIENTE'
);

-- ============================
-- TABELA: Endereco
-- ============================
CREATE TABLE endereco (
    id_endereco BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    rua VARCHAR(120),
    numero VARCHAR(5),
    complemento VARCHAR(50),
    bairro VARCHAR(120),
    cidade VARCHAR(120),
    estado VARCHAR(100),
    cep VARCHAR(12),
    padrao BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_endereco_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuario(id_usuario)
        ON DELETE CASCADE
);

-- ============================
-- TABELA: Tipo_Papelao
-- ============================
CREATE TABLE tipo_papelao (
    id_tipo_papelao BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    descricao TEXT
);

-- ============================
-- TABELA: Produto
-- ============================
CREATE TABLE produto (
    id_produto BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    altura VARCHAR(20),
    largura VARCHAR(20),
    profundidade VARCHAR(20),
    volume_suportado INT,
    quantidade_estoque INT,
    cor VARCHAR(20),
    id_tipo_papelao BIGINT NOT NULL,
    projeto_principal_nome VARCHAR(100),
    projeto_principal_descricao TEXT,
    CONSTRAINT fk_produto_tipo FOREIGN KEY (id_tipo_papelao)
        REFERENCES tipo_papelao(id_tipo_papelao)
        ON DELETE RESTRICT
);

-- ============================
-- TABELA: Preco_Produto
-- ============================
CREATE TABLE preco_produto (
    id_preco BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_produto BIGINT NOT NULL,
    valor_compra DECIMAL(10,2) NOT NULL,
    valor_venda DECIMAL(10,2) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE,
    CONSTRAINT fk_preco_produto FOREIGN KEY (id_produto)
        REFERENCES produto(id_produto)
        ON DELETE CASCADE
);

-- ============================
-- TABELA: Imagem_Produto
-- ============================
CREATE TABLE imagem_produto (
    id_imagem BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_produto BIGINT NOT NULL,
    arquivo_imagem LONGBLOB,
    tipo_imagem VARCHAR(10),
    CONSTRAINT fk_imagem_produto FOREIGN KEY (id_produto)
        REFERENCES produto(id_produto)
        ON DELETE CASCADE
);

-- ============================
-- TABELA: Tipo_Transacao
-- ============================
CREATE TABLE tipo_transacao (
    id_tipo_transacao BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao ENUM('ENTRADA', 'SAIDA', 'AJUSTE') NOT NULL
);

-- ============================
-- TABELA: Transacao_Estoque
-- ============================
CREATE TABLE transacao_estoque (
    id_transacao BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    id_produto BIGINT NOT NULL,
    id_tipo_transacao BIGINT NOT NULL,
    quantidade_alterada INT NOT NULL,
    data_hora DATETIME NOT NULL,
    observacao TEXT,
    CONSTRAINT fk_transacao_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuario(id_usuario),
    CONSTRAINT fk_transacao_produto FOREIGN KEY (id_produto)
        REFERENCES produto(id_produto),
    CONSTRAINT fk_transacao_tipo FOREIGN KEY (id_tipo_transacao)
        REFERENCES tipo_transacao(id_tipo_transacao)
);

-- ============================
-- TABELA: Pedido
-- ============================
CREATE TABLE pedido (
    id_pedido BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    id_endereco BIGINT NOT NULL,
    data_pedido DATE NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    CONSTRAINT fk_pedido_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuario(id_usuario),
    CONSTRAINT fk_pedido_endereco FOREIGN KEY (id_endereco)
        REFERENCES endereco(id_endereco)
);

-- ============================
-- TABELA: Item_Pedido
-- ============================
CREATE TABLE item_pedido (
    id_item BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_pedido BIGINT NOT NULL,
    id_produto BIGINT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_item_pedido FOREIGN KEY (id_pedido)
        REFERENCES pedido(id_pedido)
        ON DELETE CASCADE,
    CONSTRAINT fk_item_produto FOREIGN KEY (id_produto)
        REFERENCES produto(id_produto)
);

-- ============================
-- TABELA: Pagamento
-- ============================
CREATE TABLE pagamento (
    id_pagamento BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_pedido BIGINT NOT NULL,
    metodo VARCHAR(50) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    data_pagamento DATE NOT NULL,
    CONSTRAINT fk_pagamento_pedido FOREIGN KEY (id_pedido)
        REFERENCES pedido(id_pedido)
        ON DELETE CASCADE
);

-- ============================
-- TABELA: Entrega
-- ============================
CREATE TABLE entrega (
    id_entrega BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_pedido BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    data_envio DATE,
    data_entrega DATE,
    CONSTRAINT fk_entrega_pedido FOREIGN KEY (id_pedido)
        REFERENCES pedido(id_pedido)
        ON DELETE CASCADE
);

-- ============================
-- TABELA: Projeto
-- ============================
CREATE TABLE projeto (
    id_projeto BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

-- ============================
-- TABELA: Produto_Projeto
-- ============================
CREATE TABLE produto_projeto (
    id_produto BIGINT NOT NULL,
    id_projeto BIGINT NOT NULL,
    PRIMARY KEY (id_produto, id_projeto),
    CONSTRAINT fk_produto_projeto FOREIGN KEY (id_produto)
        REFERENCES produto(id_produto)
        ON DELETE CASCADE,
    CONSTRAINT fk_projeto_produto FOREIGN KEY (id_projeto)
        REFERENCES projeto(id_projeto)
        ON DELETE CASCADE
);

-- ============================
-- TABELA: Log_Auditoria
-- ============================
CREATE TABLE log_auditoria (
    id_log BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    acao VARCHAR(255) NOT NULL,
    tabela_afetada VARCHAR(100) NOT NULL,
    data_hora DATETIME NOT NULL,
    detalhes TEXT,
    CONSTRAINT fk_log_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuario(id_usuario)
);
