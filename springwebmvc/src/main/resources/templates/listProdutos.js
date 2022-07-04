

var listProdutos = [];


query.executeSql("SELECT nome FROM Produto", [listProdutos],querySuccess, errorCB,cid_value)