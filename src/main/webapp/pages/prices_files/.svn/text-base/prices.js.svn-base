/*
For functions getElementsByClassName, addClassName, and removeClassName
Copyright Robert Nyman, http://www.robertnyman.com
Free to use if this text is included
*/

function getElementsByClassName(className, tag, elm){
var testClass = new RegExp("(^|\\s)" + className + "(\\s|$)");
var tag = tag || "*";
var elm = elm || document;
var elements = (tag == "*" && elm.all)? elm.all : elm.getElementsByTagName(tag);
var returnElements = [];
var current;
var length = elements.length;
for(var i=0; i<length; i++){
current = elements[i];
if(testClass.test(current.className)){
returnElements.push(current);
}
}
return returnElements;
}
function addClassName(elm, className){
var currentClass = elm.className;
if(!new RegExp(("(^|\\s)" + className + "(\\s|$)"), "i").test(currentClass)){
elm.className = currentClass + ((currentClass.length > 0)? " " : "") + className;
}
return elm.className;
}
function removeClassName(elm, className){
var classToRemove = new RegExp(("(^|\\s)" + className + "(\\s|$)"), "i");
elm.className = elm.className.replace(classToRemove, "").replace(/^\s+|\s+$/g, "");
return elm.className;
}
function hasClass(el, c) {
if (!el || !el.className.length) return;
var bits = el.className.split(' '), has = false;
for (var j = 0; j < bits.length; j++) if (bits[j] === c) has = true;
return has;
}
function activateThisColumn(column) {
var table = document.getElementById('pricetable');
var form = document.getElementById('formcontainer');
// first, remove the 'on' class from all other th's
var ths = table.getElementsByTagName('th');
for (var g=0; g<ths.length; g++) {
removeClassName(ths[g], 'on');
if (!hasClass(ths[g],'side')) {
ths[g].style.display = 'none';
}
}
// then, remove the 'on' class from all other td's
var tds = table.getElementsByTagName('td');
for (var m=0; m<tds.length; m++) {
removeClassName(tds[m], 'on');
if (!hasClass(tds[m],'side')) {
tds[m].style.display = 'none';
}
}
// now, add the class 'on' to the selected th
var newths = getElementsByClassName(column, 'th', table);
for (var h=0; h<newths.length; h++) {
addClassName(newths[h], 'on');
newths[h].style.display = '';
// not all browsers like display = 'block' for cells
}
// and finally, add the class 'on' to the selected td
var newtds = getElementsByClassName(column, 'td', table);
for (var i=0; i<newtds.length; i++) {
addClassName(newtds[i], 'on');
newtds[i].style.display = '';
// not all browsers like display = 'block' for cells
}
// show the form!
form.style.display = 'block';
}
function hideTheForm() {
// get the form
var form = document.getElementById('formcontainer');
// hide the form
form.style.display = 'none';
// now get the hidden table cells and show them again
var table = document.getElementById('pricetable');
var tds = table.getElementsByTagName('td');
for (var i=0; i<tds.length; i++) {
tds[i].style.display = '';
}
var ths = table.getElementsByTagName('th');
for (var k=0; k<ths.length; k++) {
ths[k].style.display = '';
}
}
