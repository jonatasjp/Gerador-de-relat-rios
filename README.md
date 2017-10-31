# Repositório com a implementação de um gerador de relatórios para projeto Java, mais especificamente para projetos que usam JSF e Primefaces.

É comum em vários aplicações a necessidade de gerar algum documento, relatório, em algum momento. Sempre que nos deparamos com esse cenário pensamos em implementar classes genéricas para poder reutilizar na geração dos documentos, porém nem sempre temos tempo para fazer isso.

Como consequência do cenário acima, muitos sistemas acabam duplicando o código da geração de arquivos em vários pontos da aplicação, o que causa diversos problemas.

Este projeto consiste na implementação de classes capazes de gerar documentos em diversos formatos, utilizando o framework JasperReports.