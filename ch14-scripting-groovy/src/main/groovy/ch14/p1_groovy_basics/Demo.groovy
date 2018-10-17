package ch14.p1_groovy_basics

import java.time.LocalDate

/*
 * Does not need a main method and can be run directly
 */

//-----------------------------------------------------------------------------
// Classes
//-----------------------------------------------------------------------------

Singer singer = new Singer(firstName: 'John', lastName: 1234,
        birthDate: LocalDate.of(1977, 9, 6))

println singer

// ok, because dynamic typing
println singer.firstName + 39
println singer.lastName - 234

//-----------------------------------------------------------------------------
// Collections
//-----------------------------------------------------------------------------

def list = ['this', 'is', 'Johnny']
println list

assert list.size() == 3
assert list.class == ArrayList

assert list.reverse() == ['Johnny', 'is', 'this']

assert list.sort { it.size() } == ['is', 'this', 'Johnny']

assert list[0..1] == ['is', 'this']

//-----------------------------------------------------------------------------
// Closures
//-----------------------------------------------------------------------------

def names = ['John', 'Clayton', 'Mayer']

names.each { println 'Hello ' + it }

// closure as a variable
def map = ['a': 10, 'b': 50]

Closure square = { k, v -> map[k] = v * v }

map.each square

println map