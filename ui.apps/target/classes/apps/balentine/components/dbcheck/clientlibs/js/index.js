let aboject = {

    idfruits : 7,
	fruitName : "Cherry",
	fruitColor : "Red"

}
function Fruit(idfruits,fruitName,fruitColor){
this.idfruits = idfruits;
this.fruitName = fruitName;
this.fruitColor = fruitColor;    
}

let FruitAjaxController = (function () {
  console.log("Fruit Ajax Controller");

})();

let FruitDataController = (function(){
  console.log("Fruit Data Controller");
})();

let DataBaseAjaxController = (function(){
  console.log("Fruit DataBase Controller")
})();

$( document ).ready(function() {
    console.log( "Inside Document Ready!" );
});





/*
$.ajax({
  method: "POST",
  url: "/services/fruits/addfruit",
  contentType: "application/json",  
  data: JSON.stringify(aboject)
})
  .done(function( msg ) {
    console.log( "Data Saved: ", msg );
  });
  */