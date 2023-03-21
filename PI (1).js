const leitor = require("readline-sync")
var nomes = []
var cidades = []
var idades = []
  
inicio()

function inicio()
{
	
        nomes[nomes.length] = leitor.question("Nome --> ")
      
		cidades[cidades.length] = leitor.question("Informe o cidade --> ")
      
		idades[idades.length] = leitor.question("Informe o idade --> ")
  
        Continue()
		
}
       
function Procurar()
{
            parametro = leitor.question ("Informe o nome, a cidade ou a idade --> ")
            
                for(let i=0;i<nomes.length;i++){
                if (parametro == nomes[i] || parametro == cidades[i] || parametro == idades[i]){
                console.log("Nome: ",nomes[i]," Cidade: ",cidades[i]," Idade: ",idades[i])
                    }else{
                    console.log("Sem Resultados")  
                    }
                }
        Continue()
    
}

function Continue()
{
     op = leitor.questionInt("\n2 - Pesquisar e 1 - cadastrar 0 - Sair ... ")
    if (op == '2'){
    Procurar()
    }else if(op == '1'){
        inicio()
         }else{
         return
         } 
}
          