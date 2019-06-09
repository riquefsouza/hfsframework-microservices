insert into ADM_PROFILE (PRF_SEQ, PRF_DESCRIPTION, PRF_GENERAL, PRF_ADMINISTRATOR) values (1, 'General', 'S', 'N');
insert into ADM_PROFILE (PRF_SEQ, PRF_DESCRIPTION, PRF_GENERAL, PRF_ADMINISTRATOR) values (2, 'Administrator', 'N', 'S');

insert into ADM_PAGE (PAG_SEQ, PAG_DESCRIPTION, PAG_URL)
values (1, 'Configuration Parameters Category', 'admin/admParameterCategory/listAdmParameterCategory.html');
insert into ADM_PAGE (PAG_SEQ, PAG_DESCRIPTION, PAG_URL)
values (2, 'Edit Configuration Parameters Category', 'admin/admParameterCategory/editAdmParameterCategory.html');
insert into ADM_PAGE (PAG_SEQ, PAG_DESCRIPTION, PAG_URL) values (3, 'Configuration Parameters', 'admin/admParameter/listAdmParameter.html');
insert into ADM_PAGE (PAG_SEQ, PAG_DESCRIPTION, PAG_URL) values (4, 'Edit Configuration Parameters', 'admin/admParameter/editAdmParameter.html');
insert into ADM_PAGE (PAG_SEQ, PAG_DESCRIPTION, PAG_URL) values (5, 'Manage Profile', 'admin/admProfile/listAdmProfile.html');
insert into ADM_PAGE (PAG_SEQ, PAG_DESCRIPTION, PAG_URL) values (6, 'Edit Manage Profile', 'admin/admProfile/editAdmProfile.html');
insert into ADM_PAGE (PAG_SEQ, PAG_DESCRIPTION, PAG_URL) values (7, 'Manage Page', 'admin/admPage/listAdmPage.html');
insert into ADM_PAGE (PAG_SEQ, PAG_DESCRIPTION, PAG_URL) values (8, 'Edit Manage Page', 'admin/admPage/editAdmPage.html');
insert into ADM_PAGE (PAG_SEQ, PAG_DESCRIPTION, PAG_URL) values (9, 'Manage Menu', 'admin/admMenu/listAdmMenu.html');
insert into ADM_PAGE (PAG_SEQ, PAG_DESCRIPTION, PAG_URL) values (10, 'Edit Manage Menu', 'admin/admMenu/editAdmMenu.html');
insert into ADM_PAGE (PAG_SEQ, PAG_DESCRIPTION, PAG_URL) values (11, 'View Users', 'admin/admUser/listAdmUser.html');
insert into ADM_PAGE (PAG_SEQ, PAG_DESCRIPTION, PAG_URL) values (12, 'Edit View Users', 'admin/vwAdmLog/listVwAdmLog.html');

insert into ADM_MENU (MNU_SEQ, MNU_DESCRIPTION, MNU_PARENT_SEQ, MNU_PAG_SEQ, MNU_ORDER) values (1, 'Administrative', null, null, 1);
insert into ADM_MENU (MNU_SEQ, MNU_DESCRIPTION, MNU_PARENT_SEQ, MNU_PAG_SEQ, MNU_ORDER) values (2, 'Configuration Parameters Category', 1, 1, 2);
insert into ADM_MENU (MNU_SEQ, MNU_DESCRIPTION, MNU_PARENT_SEQ, MNU_PAG_SEQ, MNU_ORDER) values (3, 'Configuration Parameters', 1, 3, 3);
insert into ADM_MENU (MNU_SEQ, MNU_DESCRIPTION, MNU_PARENT_SEQ, MNU_PAG_SEQ, MNU_ORDER) values (4, 'Manage Profile', 1, 5, 4);
insert into ADM_MENU (MNU_SEQ, MNU_DESCRIPTION, MNU_PARENT_SEQ, MNU_PAG_SEQ, MNU_ORDER) values (5, 'Manage Page', 1, 7, 5);
insert into ADM_MENU (MNU_SEQ, MNU_DESCRIPTION, MNU_PARENT_SEQ, MNU_PAG_SEQ, MNU_ORDER) values (6, 'Manage Menu', 1, 9, 6);
insert into ADM_MENU (MNU_SEQ, MNU_DESCRIPTION, MNU_PARENT_SEQ, MNU_PAG_SEQ, MNU_ORDER) values (7, 'View Users', 1, 11, 7);

insert into ADM_USER (USU_SEQ, USU_LOGIN, USU_NAME, USU_CPF, USU_EMAIL, USU_LDAP_DN, USU_PASSWORD, created_date, modified_date, created_by, modified_by)
values (1, 'henrique.souza', 'Henrique Figueiredo de Souza', 57358497007, 'riquefsouza@gmail.com', null,'7ce0359f12857f2a90c7de465f40a95f01cb5da9',
to_date('09-06-2019 12:57:12', 'dd-mm-yyyy hh24:mi:ss'),NULL,NULL,NULL);

insert into ADM_PARAMETER_CATEGORY (PMC_SEQ, PMC_DESCRIPTION, PMC_ORDER) values (1, 'Parameters of the Court', 1);
insert into ADM_PARAMETER_CATEGORY (PMC_SEQ, PMC_DESCRIPTION, PMC_ORDER) values (2, 'Login Parameters', 2);
insert into ADM_PARAMETER_CATEGORY (PMC_SEQ, PMC_DESCRIPTION, PMC_ORDER) values (3, 'E-mail Parameters', 3);
insert into ADM_PARAMETER_CATEGORY (PMC_SEQ, PMC_DESCRIPTION, PMC_ORDER) values (4, 'Network Connection Parameters', 4);

insert into ADM_PARAMETER (PAR_SEQ, PAR_VALUE, PAR_DESCRIPTION, PAR_CODE, PAR_PMC_SEQ)
values (1, 'Tribunal Regional do Trabalho da 1a. Região', 'Nome do tribunal onde o sistema está instalado.', 'NOME_TRIBUNAL', 1);
insert into ADM_PARAMETER (PAR_SEQ, PAR_VALUE, PAR_DESCRIPTION, PAR_CODE, PAR_PMC_SEQ)
values (2, 'TRT1', 'Sigla do tribunal onde o sistema está instalado.', 'SIGLA_TRIBUNAL', 1);
insert into ADM_PARAMETER (PAR_SEQ, PAR_VALUE, PAR_DESCRIPTION, PAR_CODE, PAR_PMC_SEQ)
values (3, '080009', 'Código númérico de 6 dígitos que identifica o órgão no SIAFI.', 'CODIGO_UNIDADE_GESTORA', 1);
insert into ADM_PARAMETER (PAR_SEQ, PAR_VALUE, PAR_DESCRIPTION, PAR_CODE, PAR_PMC_SEQ)
values (4, '102', 'Código númérico de 3 dígitos da UG no código de barras da GRU.', 'APELIDO_UNIDADE_GESTORA', 1);
insert into ADM_PARAMETER (PAR_SEQ, PAR_VALUE, PAR_DESCRIPTION, PAR_CODE, PAR_PMC_SEQ)
values (5, 'false', 'Bloquear o sistema para que os usuários, exceto do administador, não façam login', 'BLOQUEAR_LOGIN', 2);
insert into ADM_PARAMETER (PAR_SEQ, PAR_VALUE, PAR_DESCRIPTION, PAR_CODE, PAR_PMC_SEQ)
values (6, 'Produção', 'Define o ambiente onde o sistema está instalado. Este parâmetro pode ser preenchido com: desenvolvimento, homologação ou produção', 'AMBIENTE_SISTEMA', 2);
insert into ADM_PARAMETER (PAR_SEQ, PAR_VALUE, PAR_DESCRIPTION, PAR_CODE, PAR_PMC_SEQ)
values (7, 'NOME_USUARIO', 'Define o atributo usado para efetuar login no sistema. Este parâmetro pode ser preenchido com: NOME_USUARIO ou CPF', 'ATRIBUTO_LOGIN', 2);
insert into ADM_PARAMETER (PAR_SEQ, PAR_VALUE, PAR_DESCRIPTION, PAR_CODE, PAR_PMC_SEQ)
values (8, 'smtp.trt1.jus.br', 'Servidor SMTP para que o sistema envie e-mail.', 'SMTP_SERVIDOR', 3);
insert into ADM_PARAMETER (PAR_SEQ, PAR_VALUE, PAR_DESCRIPTION, PAR_CODE, PAR_PMC_SEQ)
values (9, '25', 'Porta do servidor SMTP para que o sistema envie e-mail.', 'SMTP_PORTA', 3);
insert into ADM_PARAMETER (PAR_SEQ, PAR_VALUE, PAR_DESCRIPTION, PAR_CODE, PAR_PMC_SEQ)
values (10, 'smtp_user', 'Usuário para login no servidor SMTP.', 'SMTP_USERNAME', 3);
insert into ADM_PARAMETER (PAR_SEQ, PAR_VALUE, PAR_DESCRIPTION, PAR_CODE, PAR_PMC_SEQ)
values (11, 'smtp_password', 'Senha para login no servidor SMTP.', 'SMTP_PASSWORD', 3);
insert into ADM_PARAMETER (PAR_SEQ, PAR_VALUE, PAR_DESCRIPTION, PAR_CODE, PAR_PMC_SEQ)
values (12, 'sistema@trt1.jus.br', 'E-mail do sistema.', 'SMTP_EMAIL_FROM', 3);
insert into ADM_PARAMETER (PAR_SEQ, PAR_VALUE, PAR_DESCRIPTION, PAR_CODE, PAR_PMC_SEQ)
values (13, 'proxy.gov.br', 'Servidor do Proxy.', 'PROXY_SERVIDOR', 4);
insert into ADM_PARAMETER (PAR_SEQ, PAR_VALUE, PAR_DESCRIPTION, PAR_CODE, PAR_PMC_SEQ)
values (14, '8080', 'Porta do Proxy.', 'PROXY_PORTA', 4);

insert into ADM_PAGE_PROFILE (PGL_PAG_SEQ, PGL_PRF_SEQ) values (1, 2);
insert into ADM_PAGE_PROFILE (PGL_PAG_SEQ, PGL_PRF_SEQ) values (2, 2);
insert into ADM_PAGE_PROFILE (PGL_PAG_SEQ, PGL_PRF_SEQ) values (3, 2);
insert into ADM_PAGE_PROFILE (PGL_PAG_SEQ, PGL_PRF_SEQ) values (4, 2);
insert into ADM_PAGE_PROFILE (PGL_PAG_SEQ, PGL_PRF_SEQ) values (5, 2);
insert into ADM_PAGE_PROFILE (PGL_PAG_SEQ, PGL_PRF_SEQ) values (6, 2);
insert into ADM_PAGE_PROFILE (PGL_PAG_SEQ, PGL_PRF_SEQ) values (7, 2);
insert into ADM_PAGE_PROFILE (PGL_PAG_SEQ, PGL_PRF_SEQ) values (8, 2);
insert into ADM_PAGE_PROFILE (PGL_PAG_SEQ, PGL_PRF_SEQ) values (9, 2);
insert into ADM_PAGE_PROFILE (PGL_PAG_SEQ, PGL_PRF_SEQ) values (10, 2);
insert into ADM_PAGE_PROFILE (PGL_PAG_SEQ, PGL_PRF_SEQ) values (11, 2);
insert into ADM_PAGE_PROFILE (PGL_PAG_SEQ, PGL_PRF_SEQ) values (12, 2);

insert into ADM_USER_PROFILE (USP_USE_SEQ, USP_PRF_SEQ) values (1, 2);

