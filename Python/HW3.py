"""Name: Linoy Aslan
ID: 313279036
Home work number: 3
Campus: Beer-Sheva"""

# ex 1

"""This function is the basis of the API of power - there is a basis and a power and with the method of dispatch by
index I execute the API
for example: if i execute the function make_power(4,5) the basis is 4 and the power is 5"""


def make_power(b, p):
    def dispatch(i):
        if i == 0:
            return b
        elif i == 1:
            return p
        else:
            return "error"

    return dispatch


"""This function is return the basis by the index (zero). The  number zero sent to the dispatch function and return 
the basis.
For example: if x=make_power(4,5), the function 'base' return 4 """


def base(x):
    return x(0)


"""This function is return the power by the index (one). The number one sent to the dispatch function and return 
the power.
For example: if x=make_power(4,5), the function 'power' return 5"""


def power(x):
    return x(1)


"""This function print the basis and the power in a format basis^power.
For example: if x=make_power(4,5), the function print 4^5"""


def print_power(x):
    if type(x) == int:
        print(x)
    elif power(x) == 1:
        print(base(x))
    elif power(x) == 0:
        print(1)
    else:
        print("{0}^{1}".format(base(x), power(x)))


"""This function is calculate the result of the power.
For example: if x=make_power(4,5), the function calculate 4**5 and return the result: 1024"""


def calc_power(x):
    return base(x) ** power(x)


"""This function is multiple by 2 make_power. if the basis are equal the function return the basis and add the powers. 
If the basis are different the function execute the function 'calc_power' on the 2 different make_power that sent to it.
 For example: if x=make_power(3,2) and y=make_power(3,5) the function return make_power(3,7).
 If x=make_power(3,2) and y=make_power(2,3) the function calculate the 3**2 * 2**3 and return the result: 72"""


def mul_power(x, y):
    if base(x) == base(y):
        return make_power(base(x), power(x) + power(y))
    if base(x) != base(y):
        return calc_power(x) * calc_power(y)


""""This function is divide by 2 make_power. if the basis are equal the function return the basis and decrease the 
powers. If the basis are different the function execute the function 'calc_power' on the 2 different make_power that 
sent to it. For example: if x=make_power(3,2) and y=make_power(3,5) the function return make_power(3,
-3). If x=make_power(3,2) and y=make_power(2,3) the function calculate the 3**2 / 2**3 and return the result: 1.125 """


def div_power(x, y):
    if base(x) == base(y):
        return make_power(base(x), power(x) - power(y))
    if base(x) != base(y):
        return calc_power(x) / calc_power(y)


"""This function check if it is possible to reduce the base and increase the power and to get minimal base and power 
and the same result of calculating. For example: if x=make_power(4,2) and the result of the calculating is 16, 
the function return make_power(2,4) """


def improve_power(x):
    a = base(x) - 1
    n = 1
    flag = 1
    temp_a = 1
    temp_n = 1
    while flag != 0:
        if base(x) ** power(x) < a ** n:
            a -= 1
            n = 1
        if base(x) ** power(x) == a ** n:
            temp_a = a
            temp_n = n
            a -= 1
            n = 1
        if a == 1:
            flag = 0
        else:
            n += 1
    if base(x) ** power(x) == temp_a ** temp_n:
        return make_power(temp_a, temp_n)
    return make_power(base(x), power(x))


x = make_power(4, 5)
print(x)
print(base(x))
print(power(x))
print_power(x)
print_power(improve_power(x))
print_power(mul_power(improve_power(x), make_power(2, 5)))
y = make_power(9, 2)
print_power(improve_power(y))
print_power(mul_power(x, y))
print_power(mul_power(improve_power(y), make_power(3, 5)))
print_power(div_power(improve_power(y), make_power(3, 5)))
print_power(div_power(mul_power(make_power(2, 3), make_power(2, 8)), make_power(2, 4)))
print_power(div_power(mul_power(improve_power(make_power(8, 1)), improve_power(make_power(256, 1))),
                      improve_power(make_power(16, 1))))
print_power(make_power(12, 1))
print_power(make_power(12, 0))

# ex 2


"""This function is the basis of the API of binary tree - there is a value, left of tree and right of tree and with 
the method of dispatch by index I execute the API.
For example: if i execute the function make_tree(12, make_tree(6,
None, None), make_tree(8, None, None)) The root is 12, the left son is 6 (and this son do not have children) and the 
right son is 8 (and this son do not have children) """


def make_tree(val, left, right):
    def dispatch(i):
        if i == 0:
            return val
        if i == 1:
            return left
        if i == 2:
            return right
        else:
            return "error"

    return dispatch


"""This Function is return the value of a node.
For example: make_tree(12, make_tree(6,None, None), make_tree(8, None, None)) - the function return 12."""


def value(tree):
    return tree(0)


"""This function is return the left son of a node.
For example: make_tree(12, make_tree(6,None, None), make_tree(8, None, None)) - the function return 6"""


def left(tree):
    return tree(1)


"""This function is return the right son of a node.
For example: make_tree(12, make_tree(6,None, None), make_tree(8, None, None)) - the function return 8"""


def right(tree):
    return tree(2)


"""The function is printing the tree by the scan 'inorder' (left, root, right).
For example: make_tree(12, make_tree(6,None, None), make_tree(8, None, None)) - the function print 6 12 8."""


def print_tree(tree):
    if tree is not None:
        print_tree(left(tree))
        print(value(tree), end=" ")
        print_tree(right(tree))


"""This function get a tree and a value and return how many times this value is show in the tree.
For example: the tree make_tree(12, make_tree(6,None, None), make_tree(8, None, None)) and the value 8,
this function return: 1"""


def count_value(tree, val):
    if tree is None:
        return 0
    if value(tree) == val:
        return 1 + count_value(left(tree), val) + count_value(right(tree), val)
    return count_value(left(tree), val) + count_value(right(tree), val)


"""This function is an helping function. return the max value in the tree.
For example: make_tree(12, make_tree(6,None, None), make_tree(8, None, None)) ,this function return 12."""


def max_value(tree):
    if tree is None:
        return -1000000000
    maxi = value(tree)
    rightMax = max_value(right(tree))
    leftMax = max_value(left(tree))
    if rightMax > maxi:
        maxi = rightMax
    if leftMax > maxi:
        maxi = leftMax
    return maxi


"""This function is an helping function. return the min value in the tree.
For example: make_tree(12, make_tree(6,None, None), make_tree(8, None, None)), this function return 6."""


def min_value(tree):
    if tree is None:
        return 1000000000
    mini = value(tree)
    rightMin = min_value(right(tree))
    leftMin = min_value(left(tree))
    if rightMin < mini:
        mini = rightMin
    if leftMin < mini:
        mini = leftMin
    return mini


"""This function check if the tree that send to it is a BST. BST is a binary search tree - the nodes in the left 
subtree are smaller than the root, and the nodes in the right subtree are bigger. For example: make_tree(12, 
make_tree(6,None, None), make_tree(8, None, None)) - the function return false - because 8 that in the right of the 
root is smaller than 12 (the root)"""


def tree_Bst(tree):
    if tree is None:
        return True
    if max_value(left(tree)) > value(tree):
        return False
    if min_value(right(tree)) < value(tree):
        return False
    return tree_Bst(left(tree)) and tree_Bst(right(tree))


"""This function return the depth of the tree.
For example: make_tree(12, make_tree(6,None, None), make_tree(8, None, None)) - the function return 1"""


def tree_depth(tree):
    if tree is None:
        return -1
    else:
        depthL = tree_depth(left(tree))
        depthR = tree_depth(right(tree))
        if depthL > depthR:
            return depthL + 1
        else:
            return depthR + 1


"""This function return true if a tree that sent to it is balanced (balance = |left(tree)-right(tree)| <= 1).
For example: make_tree(12, make_tree(6,None, None), make_tree(8, None, None)) - the function return True."""


def tree_balanced(tree):
    if tree is None:
        return True
    if abs(tree_depth(left(tree)) - tree_depth(right(tree))) > 1:
        return False
    return tree_balanced(left(tree)) and tree_balanced(right(tree))


tree1 = make_tree(12, make_tree(6, make_tree(8, None, None), None),
                  make_tree(7, make_tree(8, None, None), make_tree(15, None, None)))
tree2 = make_tree(12, make_tree(6, make_tree(3, make_tree(1, None, None), None),
                                make_tree(8, make_tree(7, None, None), None)),
                  make_tree(15, None, make_tree(20, make_tree(17, None, None), None)))
print(tree1)
print(tree2)
print(value(tree1))
print(value(left(tree1)))
print(value(right(left(tree2))))
print_tree(tree1)
print("")
print_tree(tree2)
print("")
print(count_value(tree1, 8))
print(tree_Bst(tree1))
print(tree_Bst(tree2))
print(tree_depth(tree1))
print(tree_depth(tree2))
print(tree_balanced(tree1))
print(tree_balanced(tree2))

# ex 3

from functools import reduce

"""This function is execute the principle of pipeline and conventional interface. The function get a name of a store, 
a tuple of products and their price ,and a tuple of stores and the sale, and return a tuple of products and the price 
after the sale. For example: if the tuple of products and their price is: (('p1',1000),('p2',2000),('p3',5000),('p4',
100)), and the tuple of the stores and the sales is: (('s1',0.2),('s2',0.3),('s3',0.1)) the function return - (('p1', 
800.0), ('p2', 1600.0), ('p3', 4000.0), ('p4', 80.0)). """


def get_prices(name, products, sales):
    return tuple((a, b - (tuple(filter(lambda x: x[0] == name, sales)))[0][1] * b) for a, b in products)


"""This function is execute the principle of pipeline and conventional interface. The function get a name of a store, 
a dictionary of products and their price ,and a dictionary of stores and the sale, and return a dictionary of 
products and the price after the sale. For example: if the dictionary of products and their price is: {'p1':1000,'p2':
2000,'p3':5000,'p4': 100}, and the dictionary of the stores and the sales is: {'s1':0.2,'s2':0.3,'s3':
0.1)} the function return - {'p1': 800.0, 'p2': 1600.0, 'p3': 4000.0, 'p4': 80.0}. """


def get_prices_dict(name, prod_dict, sale_dict):
    return {a: (b - (sale_dict.get(name)) * b) for (a, b) in prod_dict.items()}


"""This function is execute the principle of pipeline and conventional interface. The function get a name of a store, 
a dictionary of products and their price ,a nested dictionary of stores and the type of sales, and a dictionary of 
types of products, and return a dictionary of products and the price after the sale. For example: if the dictionary 
of products and their price is: {'p1':1000,'p2': 2000,'p3':5000,'p4': 100}, and the dictionary of the stores and the 
sales by type is: {'s1':{'t1':0.2, 't2':0.1}, 's2':{'t1':0.1, 't2':0.2},'s3':{'t1':0.3, 't2':0.5}}, and dictionary of 
types of products is: {'t1':('p2', 'p4'), 't2':('p1', 'p3')} the function return - {'p1': 900.0, 'p2': 1600.0, 
'p3': 4500.0, 'p4': 80.0}. """


def get_prices_by_type(name, prod_dict, sales, types):
    return {a: (b - sales.get(name).get(tuple(filter(lambda x: True if a in types[x] else False, types))[0]) * b) for
            a, b in prod_dict.items()}


"""This function is execute the principle of pipeline and conventional interface. The function get a name of a store, 
a dictionary of products and their price ,a nested dictionary of stores and the type of sales ,a dictionary of types 
of products, and a adding function and return the result of the adding all the prices of the products after the sale. 
For example: if the dictionary of products and their price is: {'p1':1000,'p2': 2000,'p3':5000,'p4': 100}, 
and the dictionary of the stores and the sales by type is: {'s1':{'t1':0.2, 't2':0.1}, 's2':{'t1':0.1, 't2':0.2},
's3':{'t1':0.3, 't2':0.5}}, and dictionary of types of products is: {'t1':('p2', 'p4'), 't2':('p1', 'p3')} the 
function return - 7080.0. """


def accumulate_prices(name, prod_dict, sales, types, add):
    return reduce(add,
                  {a: (b - sales.get(name).get(tuple(filter(lambda x: True if a in types[x] else False, types))[0]) * b)
                   for
                   a, b in prod_dict.items()}.values())


products = (('p1', 1000), ('p2', 2000), ('p3', 5000), ('p4', 100))
sales = (('s1', 0.2), ('s2', 0.3), ('s3', 0.1))
print(get_prices('s1', products, sales))
prod_dict = dict(products)
sale_dict = dict(sales)
print(get_prices_dict('s1', prod_dict, sale_dict))
sales = {'s1': {'t1': 0.2, 't2': 0.1}, 's2': {'t1': 0.1, 't2': 0.2}, 's3': {'t1': 0.3, 't2': 0.5}}
types = {'t1': ('p2', 'p4'), 't2': ('p1', 'p3')}
print(get_prices_by_type('s1', prod_dict, sales, types))
print(accumulate_prices('s1', prod_dict, sales, types, lambda x, y: x + y))

# ex 4

import random


"""This function is using the principle of API by the method of dispatch function. The finction coding is contains 
some function that by all that function a sentence could be encoded.
The examples I wrote for every function that in the function coding."""


def coding():
    encodingKeyDict = None

    """This function is get a tuple of shifting of the dictionary and 'yes' or 'no' for every flag.
    Flag 1 - is the reverse word
    Flag 2 - is the reverse string
    the function is change the empty key (the dictionary) and return 'done' when all the actions are done.
    For example: if code1 = coding() and code1('set_key',(-3,'yes','yes')) the function return 'done'.
    """
    def set_key(itsATuple):
        nonlocal encodingKeyDict
        encodingKeyDict = {'a': 'a', 'b': 'b', 'c': 'c', 'd': 'd', 'e': 'e',
                           'f': 'f', 'g': 'g', 'h': 'h', 'i': 'i', 'j': 'j', 'k': 'k', 'l': 'l', 'm': 'm', 'n': 'n',
                           'o': 'o', 'p': 'p', 'q': 'q', 'r': 'r', 's': 's', 't': 't', 'u': 'u', 'v': 'v', 'w': 'w',
                           'x': 'x', 'y': 'y', 'z': 'z'}

        l = list(encodingKeyDict.values())
        if itsATuple[0] == 0:
            l = l[random.randrange(-26, 26):] + l[:random.randrange(-26, 26)]
        else:
            l = l[itsATuple[0]:] + l[:itsATuple[0]]

        for k, v in zip(encodingKeyDict, l):
            encodingKeyDict[k] = v

        encodingKeyDict = dict(
            list({'reverse_word': False, 'reverse_string': False}.items()) + list(encodingKeyDict.items()))

        if itsATuple[1] == 'yes':
            encodingKeyDict['reverse_word'] = True
        elif itsATuple[1] == 'no':
            encodingKeyDict['reverse_word'] = False
        else:
            return 'error'
        if itsATuple[2] == 'yes':
            encodingKeyDict['reverse_string'] = True
        elif itsATuple[2] == 'no':
            encodingKeyDict['reverse_string'] = False
        else:
            return 'error'

        return 'done'
    """This function is show the dictionary after the shifting. The first and second keys are the flags and their 
    values are true or false. For example: if the shifting is -3, for key 'a' in the dictionary the value is 'x' and 
    so on. """
    def export_key():
        if encodingKeyDict is None:
            return 'key empty'
        return encodingKeyDict

    """This function is get a sentence and encoding it by 3 things: every character in the sentence is replace by the 
    character according to the dictionary after the shifting. if flag 1 is 'yes' so every word will changed by the 
    order of the character (reverse). if flag 2 is 'yes' so all the sentence will changed by the order of the words (
    reverse). for example: if code1('set_key',(-3,'yes','yes')) and cstr=code1('encoding','the london is the capital 
    of great britain' the function return 'kfxqfoy qxbod cl ixqfmxz beq pf klakli beq') """
    def encoding(string):
        if encodingKeyDict is None:
            return 'key empty'
        newList = list(string)
        for i in range(len(newList)):
            if newList[i] == ' ':
                i += 1
            else:
                newList[i] = encodingKeyDict[newList[i]]
        string = ""
        for j in newList:
            string += j
        if encodingKeyDict['reverse_word']:
            newList = list(string.split(" "))
            for k in range(len(newList)):
                newList[k] = newList[k][::-1]
            newList = " ".join(newList)
            string = ""
            for j in newList:
                string += j
        if encodingKeyDict['reverse_string']:
            newList = list(string.split(" "))
            newList = newList[::-1]
            newList = " ".join(newList)
            string = ""
            for j in newList:
                string += j

        return string

    """This function is decoding a sentence that send to it.
    For example: if 'kfxqfoy qxbod cl ixqfmxz beq pf klakli beq' sent to her,
    the function return 'the london is the capital of great britain' """
    def decoding(string):
        if encodingKeyDict is None:
            return 'key empty'
        newDict = {b: a for a, b in encodingKeyDict.items() if (a != 'reverse_word' and a != 'reverse_string')}
        newDict['reverse_string'] = encodingKeyDict['reverse_string']
        newDict['reverse_word'] = encodingKeyDict['reverse_word']
        newList = list(string)
        for i in range(len(newList)):
            if newList[i] == ' ':
                i += 1
            else:
                newList[i] = newDict[newList[i]]
        string = ""
        for j in newList:
            string += j
        if newDict['reverse_word']:
            newList = list(string.split(" "))
            for k in range(len(newList)):
                newList[k] = newList[k][::-1]
            newList = " ".join(newList)
            string = ""
            for j in newList:
                string += j
        if newDict['reverse_string']:
            newList = list(string.split(" "))
            newList = newList[::-1]
            newList = " ".join(newList)
            string = ""
            for j in newList:
                string += j

        return string

    """This function is import a key that sent to it and return 'done'. For example: if code2 = coding() and code2(
    'import_key')(key) the function update the key that was create at the coding function to the key that sent to it 
    and return 'done' """
    def import_key(key):
        nonlocal encodingKeyDict
        encodingKeyDict = key.copy()
        return 'done'

    """This function is nullify the key and return done.
    For example: if code2 = coding(), and code2('import_key)(key)
    and code2('empty_key') the function nullify the dictionary (the key) and return 'done'. """
    def empty_key():
        nonlocal encodingKeyDict
        encodingKeyDict = None
        return 'done'

    def dispatch(message, parameter=None):
        if message == 'set_key':
            return set_key(parameter)
        elif message == 'export_key':
            return export_key()
        elif message == 'encoding':
            return encoding(parameter)
        elif message == 'decoding':
            return decoding(parameter)
        elif message == 'import_key':
            return import_key(parameter)
        elif message == 'empty_key':
            return empty_key()

    return dispatch


code1 = coding()
print(code1('set_key', (-3, 'yes', 'yes')))
key = code1('export_key')
print(key)
cstr = code1('encoding', 'the london is the capital of great britain')
print(cstr)
dstr = code1('decoding', cstr)
print(dstr)
code2 = coding()
dstr = code2('decoding', cstr)
print(dstr)
print(code2('import_key', key))
dstr = code2('decoding', cstr)
print(dstr)
print(code2('empty_key'))
print(code2('export_key'))


# ex 5
"""This function is execute the principle of API by use the method of dispatch dictionary.
The function get a pay per hour, and a number of places that remaining for every type of parking.
All the examples I wrote for every function that are in that function"""

def parking(payPerHour, numOfReg, numOfPrior, numOfVIP):
    listOfCars = []

    """This function is get a num of car and type of the parking ,and insert to a list another lost every time this 
    function is execute. every sub list is contain a num of car, type of parking and num of hours in the parking.
    if the number of places of the type of parking is zero, the function return that the parking is full.
    For example:  park1=parking(10,3,3,3),  park1['start_parking'](222,'Regular'), so now the list is:
    [ [222, Regular, 1] ]."""
    def start_parking(numOfCar, typeOfParking):
        nonlocal numOfReg, numOfPrior, numOfVIP
        nonlocal listOfCars
        data = [numOfCar, typeOfParking, 1]
        if typeOfParking == 'Regular' and numOfReg == 0:
            ans = typeOfParking + ' parking is full'
            return ans
        elif typeOfParking == 'Priority' and numOfPrior == 0:
            ans = typeOfParking + ' parking is full'
            return ans
        elif typeOfParking == 'VIP' and numOfVIP == 0:
            ans = typeOfParking + ' parking is full'
            return ans
        if typeOfParking == 'Regular' and numOfReg != 0:
            numOfReg -= 1
            listOfCars.append(data)
        elif typeOfParking == 'Priority' and numOfPrior != 0:
            numOfPrior -= 1
            listOfCars.append(data)
        elif typeOfParking == 'VIP' and numOfVIP != 0:
            numOfVIP -= 1
            listOfCars.append(data)

    """This function is update the num of hours of all the cars in the list.
    for example: if park1=parking(10,3,3,3),  park1['start_parking'](222,'Regular') ->
    the list is [ [ 222, Regular, 1] ] and if park1['next_time'] the list is -> [ [ 222, Regular , 2] ]."""
    def next_time():
        nonlocal listOfCars
        for i in range(len(listOfCars)):
            listOfCars[i][2] += 1

    """This function print all the cars in the list.
    For example: if the list is [ [ 222, Regular, 1 ] ],
    the function will print:
    car: 222, parking type: Regular, parking time: 1"""
    def print_list():
        index = 0

        def end():
            if index >= len(listOfCars):
                return True
            return False

        def next():
            nonlocal index
            print("car: ", listOfCars[index][0], ",parking type: ", listOfCars[index][1], ",parking time: ",
                  listOfCars[index][2])
            index += 1

        return {'end': end, 'next': next}

    """This function print the sub lists that are from the type that this function get.
    For example: if the list is [ [222, Regular, 1], [223, VIP, 1]] 
    and the type is - VIP
    the function will print: car: 223, parking time: 1"""
    def print_parking(type):
        for i in range(len(listOfCars)):
            if type == listOfCars[i][1]:
                print("car: ", listOfCars[i][0], " ,parking time: ", listOfCars[i][2])

    """This function is get a num of car and return the data of that car and pop the sub list of this car from the 
    list. if the num of car that the function get is not found in the list the function return 'car not found'. For 
    example: if car 223 is sent, the function return: car:223, parking type: Regular, parking time: 2, payment: 20 """
    def end_parking(numOfCar):
        for i in range(len(listOfCars)):
            if numOfCar == listOfCars[i][0]:
                payment = listOfCars[i][2] * payPerHour
                ans = 'car: ' + str(numOfCar) + ' ,parking type: ' + str(listOfCars[i][1]) + ' ,parking time: ' + str(
                    listOfCars[i][
                        2]) + ' ,payment: ' + str(payment)
                listOfCars.pop(i)
                return ans
        return 'car not found'

    return {'print_list': print_list, 'print_parking': print_parking, 'next_time': next_time,
            'start_parking': start_parking, 'end_parking': end_parking}


park1 = parking(10, 3, 3, 3)
print(park1)
park1['start_parking'](222, 'Regular')
park1['start_parking'](223, 'Regular')
park1['next_time']()
park1['start_parking'](224, 'Regular')
print(park1['start_parking'](225, 'Regular'))
park1['start_parking'](225, 'VIP')
prn = park1['print_list']()
print(prn)
while not prn['end']():
    prn['next']()
print("")
park1['print_parking']('VIP')
print(park1['end_parking'](100))
print(park1['end_parking'](223))
park1['print_parking']('Regular')
