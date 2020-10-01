"""Name: Linoy Aslan
ID: 313279036
Campus: Beer-Sheva"""

from math import sqrt

"""Ex 1"""
"""This function is get a function and return another function that use as iterator.
That iterator allow the programmer to pass all the values without a relation to the implementation.
For example: if we send the function 2x to the function "Make_iterator" the output on a for loop of a range 0 to 4
will be: 0 2 4 6 """
fn = lambda n: 2 * n


def make_iterator(f):
    count = -1

    def increaseCounter():
        nonlocal count
        count += 1
        return f(count)

    return increaseCounter


iterator = make_iterator(fn)
it = make_iterator(fn)

for i in range(4):
    print(iterator())

print("")

for i in range(2, 4):
    print(iterator())

print("")

for i in range(2, 4):
    print(iterator())

print("")

for i in range(2):
    print(it())

print("")

for i in range(2, 4):
    print(iterator())

"""Ex 2"""
"""In that ex there is 2 help functions that i used on the activation of the 2 function that i has to write"""
print("")

"""This function is a boolean function that return true if a number is a prime number and false otherwise
For example: it return true if 3 is sent to the function, and it return false if 4 is sent to the function."""


def isPrime(num):
    flag = 0
    j = 2
    if num == 2:
        return True
    if num == 1 or num == 0:
        return False
    while j != num and flag == 0:
        if num % j == 0:
            flag = 1
        else:
            j += 1
    if flag == 1:
        return False
    return True


"""This function is a boolean function that return true if a number is a number of the Fibonacci series and false 
otherwise For example: it will return true is 13 is the number the send to the function, and it will return false if 
7 is sent to the function """


def isFib(num):
    if num == 0 or num == 1:
        return True
    newList = [0, 1]
    sumVal = 0
    k = 0
    while sumVal < num:
        sumVal = newList[k] + newList[k + 1]
        newList = newList + [sumVal]
        k += 1
    indexList = len(newList) - 1
    if num == (newList[indexList - 1] + newList[indexList - 2]):
        return True
    return False


"""This function get a list and a boolean function and filter the list that send by the function that send.
The function return a list that it is a result of the filtering.
For example: if the function get the list: [1,2,3,4,5] and  get the function "isPrime", it return the list: [2,3,5]"""


def listFilter(aList, f):
    returnList = []
    for a in range(len(aList)):
        if f(aList[a]):
            returnList.append(aList[a])
    return returnList


print(listFilter([2, 4, 5, 6, 7, 13], isPrime))

"""This function is filter a list that sent by 2 function that sent. The function return a new list that it is a 
result of the filtering that done. For example: if the function get the list: [1,2,3,4,5] and the function "isFib" 
and "isPrime", the return list will be: [2,3,5] """


def listFilterMulti(numList, funcList):
    aReturnList = []
    count = 0
    for b in range(len(numList)):
        for c in range(len(funcList)):
            if funcList[c](numList[b]):
                count += 1
        if count == len(funcList):
            aReturnList.append(numList[b])
        count = 0
    return aReturnList


print(listFilterMulti([2, 4, 5, 6, 7, 13], [isPrime, isFib]))

"""Ex 3"""

print("")

"""This algorithm is compose from 2 function - The first function is a boolean function "approx_eq" that return true 
if the absolute value of the difference between the numbers is bigger or equal to the epsilon number that i defined, 
and false otherwise. The function "Fixed_point" is using the function "approx_eq" and check if a function that sent to 
it had a fixed point or not. There is a test threshold that the function used on the checking process.
If there is a fixed point the function return the value of the fixed point, otherwise it will return none.
For example: the function x^2 return none, but the function sqrt of x return 1. """


def approx_eq(x, y, tolerance=1.0e-20):
    return abs(x - y) >= tolerance


def Fixed_point(fu, n):
    y = n
    x = fu(n)
    for _ in range(50):
        if approx_eq(x, y):
            if x > y:
                return None
        y = fu(y)
        x = fu(y)
    return x


print(Fixed_point(lambda x: sqrt(x), 1000000000000000000))
print(Fixed_point(lambda x: x ** 2, 4))
