INSERT INTO adm_parametro_categoria (pmc_seq, pmc_descricao, pmc_ordem) VALUES(1, 'Parâmetros do Tribunal', 1);
INSERT INTO adm_parametro_categoria (pmc_seq, pmc_descricao, pmc_ordem) VALUES(2, 'Parâmetros de Login', 2);
INSERT INTO adm_parametro_categoria (pmc_seq, pmc_descricao, pmc_ordem) VALUES(3, 'Parâmetros de E-mail', 3);
INSERT INTO adm_parametro_categoria (pmc_seq, pmc_descricao, pmc_ordem) VALUES(4, 'Parâmetros de Conexão de Rede', 4);
INSERT INTO adm_parametro_categoria (pmc_seq, pmc_descricao, pmc_ordem) VALUES(5, 'Objetos do Sistema', 5);


INSERT INTO adm_parametro (par_seq, par_valor, par_descricao, par_codigo, par_pmc_seq) VALUES(1, 'Tribunal Regional do Trabalho da 1a. Região', 'Nome do tribunal onde o sistema está instalado.', 'NOME_TRIBUNAL', 1);
INSERT INTO adm_parametro (par_seq, par_valor, par_descricao, par_codigo, par_pmc_seq) VALUES(2, 'TRT1', 'Sigla do tribunal onde o sistema está instalado.', 'SIGLA_TRIBUNAL', 1);
INSERT INTO adm_parametro (par_seq, par_valor, par_descricao, par_codigo, par_pmc_seq) VALUES(3, '080009', 'Código númérico de 6 dígitos que identifica o órgão no SIAFI.', 'CODIGO_UNIDADE_GESTORA', 1);
INSERT INTO adm_parametro (par_seq, par_valor, par_descricao, par_codigo, par_pmc_seq) VALUES(4, '102', 'Código númérico de 3 dígitos da UG no código de barras da GRU.', 'APELIDO_UNIDADE_GESTORA', 1);
INSERT INTO adm_parametro (par_seq, par_valor, par_descricao, par_codigo, par_pmc_seq) VALUES(5, 'false', 'Bloquear o sistema para que os usuários, exceto do administador, não façam login', 'BLOQUEAR_LOGIN', 2);
INSERT INTO adm_parametro (par_seq, par_valor, par_descricao, par_codigo, par_pmc_seq) VALUES(6, 'Produção', 'Define o ambiente onde o sistema está instalado. Este parâmetro pode ser preenchido com: desenvolvimento, homologação ou produção', 'AMBIENTE_SISTEMA', 2);
INSERT INTO adm_parametro (par_seq, par_valor, par_descricao, par_codigo, par_pmc_seq) VALUES(7, 'NOME_USUARIO', 'Define o atributo usado para efetuar login no sistema. Este parâmetro pode ser preenchido com: NOME_USUARIO ou CPF', 'ATRIBUTO_LOGIN', 2);
INSERT INTO adm_parametro (par_seq, par_valor, par_descricao, par_codigo, par_pmc_seq) VALUES(8, 'smtp.trt1.jus.br', 'Servidor SMTP para que o sistema envie e-mail.', 'SMTP_SERVIDOR', 3);
INSERT INTO adm_parametro (par_seq, par_valor, par_descricao, par_codigo, par_pmc_seq) VALUES(9, '25', 'Porta do servidor SMTP para que o sistema envie e-mail.', 'SMTP_PORTA', 3);
INSERT INTO adm_parametro (par_seq, par_valor, par_descricao, par_codigo, par_pmc_seq) VALUES(10, NULL, 'Usuário para login no servidor SMTP.', 'SMTP_USERNAME', 3);
INSERT INTO adm_parametro (par_seq, par_valor, par_descricao, par_codigo, par_pmc_seq) VALUES(11, NULL, 'Senha para login no servidor SMTP.', 'SMTP_PASSWORD', 3);
INSERT INTO adm_parametro (par_seq, par_valor, par_descricao, par_codigo, par_pmc_seq) VALUES(12, 'sistema@trt1.jus.br', 'E-mail do sistema.', 'SMTP_EMAIL_FROM', 3);
INSERT INTO adm_parametro (par_seq, par_valor, par_descricao, par_codigo, par_pmc_seq) VALUES(13, 'bravo.trtrio.gov.br', 'Servidor do Proxy.', 'PROXY_SERVIDOR', 4);
INSERT INTO adm_parametro (par_seq, par_valor, par_descricao, par_codigo, par_pmc_seq) VALUES(14, '8080', 'Porta do Proxy.', 'PROXY_PORTA', 4);
INSERT INTO adm_parametro (par_seq, par_valor, par_descricao, par_codigo, par_pmc_seq) VALUES(15, '[ 
{ "ativo": "false", "login" : "andre.fettermann", "setor" : "ESACS RJ", "cargo": "15426", "loginVirtual": "" }, 
{ "ativo": "false", "login" : "rafael.remiro", "setor" : "ESACS RJ", "cargo": "15426", "loginVirtual": "" }, 
{ "ativo": "false", "login" : "fabricio.peres", "setor" : "SAM", "cargo": "15426", "loginVirtual": "" }, 
{ "ativo": "false", "login" : "henrique.souza", "setor" : "ESACS RJ", "cargo": "15426", "loginVirtual": "fabricio.peres" }
]', 'Habilitar o modo de teste por login, esquema do json [  { "ativo": "false", "login" : "fulano", "setor" : "DISAD", "cargo": "15426" } ]', 'MODO_TESTE', 2);


