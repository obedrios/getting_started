// === Literals and Variables ===
// Define variables with var and let
var person_name = 'John Doe'; // Classic Way to declare a variable
let person_age = 40;          // ES6 - Modern way to declare a variable

let mypredicate = 1 < 10


// === Core Data structures Arrays and JSON ===
// Arrays
var colors = ["Red", "Green", "Blue"]                //Array definition
var carbrands = new Array("Suzuki", "Ford", "Honda") // Another way to declare array
let mixedtypes = ["Element 0", 12, 3.14]             // mixedtypes[1] => 12: Mixed types array

console.log(colors.length)
console.log(colors[0])
carbrands.forEach(item => console.log(item))
colors.sort().forEach(element => {
    console.log(element)
});

console.log(colors.reverse())


// Javascript Object
let book = {
    name: "Getting Started with JavaScript",
    author: "John Doe",
    edition: 10
}

// 
console.log(`Book Object contents: ${book['name']}, ${book['author']}, ${book['edition']}`)
book['edition'] = 9
book['author'] = 'Jane Doe'
console.log(`Book Object contents: ${book['name']}, ${book['author']}, ${book['edition']}`)
// Also is possible to acess properties using: book.name, book.edition
book["isbn"] = "123-4567-8910"


// Query the type of a variable
typeof(colors)          // => 'object': object type
typeof(person_age)      // => 'number': number type
typeof(person_name)     // => 'string': string type
typeof(mypredicate)     // => 'boolean': boolean type


// === Flow control in JavaScript ===
// Pattern matching example
var selectedColor = "Blue"
switch(selectedColor){
    case "Red": 
        console.log("Red Is Selected")
        break
    case "Green":
        console.log("Green Is Selected")
        break
    case "Blue":
        console.log("Blue is selected")
        break
    default: console.log("Something else")
}

// Loops Overview in Javascript
for(var index = 0; index < 10; index++) console.log(index)

for(var item of colors) console.log("This is the color " + item)

var count = 5
while(count != 0) {
    console.log("This is the count " + count)
    count = count - 1
}

// === Functions ===
function fadd(x, y) { return x + y } // Explicit functio definition
function fsub(x, y){ return x - y }
let fprod = (x, y) => x*y;           // Inline function using arrow => operator
let fapply = (f, a, b)  => f(a,b)    // Inline first-citizen function example

// Apply array of functions example
let farray = [fadd, fsub, fprod]
for(let f of farray){
    console.log(fapply(f, 1, 2))
}


// === Object Oriented Programming with JavaScript ===
class Point {              // By convention, class names are capitalized.
    constructor(x, y) {    // Constructor function to initialize new instances.
        this.x = x;        // This keyword is the new object being initialized.
        this.y = y;        // Store function arguments as object properties.
    }                      // No return is necessary in constructor functions.

    distance() {           // Method to compute distance from origin to point.
        return Math.sqrt(  // Return the square root of x² + y².
            this.x * this.x +  // this refers to the Point object on which
            this.y * this.y    // the distance method is invoked.
        );
    }
}

// Extension functions
Point.prototype.add = function add(p2){
    return new Point(p2.x + this.x, p2.y + this.y)
}


let p1 = new Point(1, 2)
let p2 = new Point(4, 5)
console.log(p1.distance())
console.log(p1.add(p2))


