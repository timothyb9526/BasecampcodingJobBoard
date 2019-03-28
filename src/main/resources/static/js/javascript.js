
function myFunction() {

  var input, filter, ul, li, a, i, txtValue;
  input = document.getElementById('myInput');
  filter = input.value.toUpperCase();
  ul = document.getElementById("myUL");
  li = ul.querySelectorAll('#employers');


  for (i = 0; i < li.length; i++) {
    a = li[i].getElementsByTagName("a")[0];
    txtValue = a.textContent || a.innerText;
    if (txtValue.toUpperCase().indexOf(filter) > -1) {
      li[i].style.display = "";
    } else {
      li[i].style.display = "none";
    }
  }
}
var count = 0
var list = document.getElementById("myUL");
var listItem = list.querySelectorAll('li');
for (var i=0; i < listItem.length; i++) {
    listItem[i].setAttribute("id", count += 1);
    console.log(listItem[i]);
}
