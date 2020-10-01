""" Name: Linoy Aslan
HW: 4
Campus: Beer-Sheva"""

# part 1 - ex 1
"""That class is represent a date - year, month and day. In that class there is __repr__ method, __str__ method,
and the __init__ method.
For example: today = Date(2017,1,20) - the year is 2017, month is 1, and day is 20."""


class Date(object):
    """the constructor"""

    def __init__(self, year=0, month=0, day=0):
        self.year = year
        if month > 12 or month < 1:
            self.month = 0
        else:
            self.month = month
        if day > 31 or day < 1:
            self.day = 0
        else:
            self.day = day

    """The __str__ method is return a string that represent the Date in a format the humans usually read a date.
    For example: today = Date(2017,1,20). print(today) will return - '20th of january, 2017' """

    def __str__(self):
        months = {1: 'January', 2: 'February', 3: 'March', 4: 'April', 5: 'May', 6: 'June', 7: 'July', 8: 'August',
                  9: 'September', 10: 'October', 11: 'November', 12: 'December'}
        return '{0}th of {1}, {2}'.format(self.day, months[self.month], self.year)

    """The __repr__ method is return a string that represent the Date in a format of object as it represent in OOP.
    For example: today = Date(2017,1,20). >>>today and enter in IDLE will return - Date(2017,1,20)"""

    def __repr__(self):
        return 'Date({0},{1},{2})'.format(self.year, self.month, self.day)


"""That class is represent a time - hours and minutes. In that class there is __repr__ method, __str__ method,
and the __init__ method.
For example: t = Time(10,0) - the hour is 10, and the minutes is 0."""


class Time(object):
    """the constructor"""

    def __init__(self, hours=0, minutes=0):
        if hours < 0 or hours > 23:
            self.hours = 0
        else:
            self.hours = hours
        if minutes < 0 or minutes > 59:
            self.minutes = 0
        else:
            self.minutes = minutes

    """The __str__ method is return a string that represent the Time in a format the humans usually read a time.
    For example: t = Time(10,0). str(t) will return - '10:00' """

    def __str__(self):
        return '{0:02d}:{1:02d}'.format(self.hours, self.minutes)

    """The __repr__ method is return a string that represent the Time in a format of object as it represent in OOP.
    For example: t = Time(10,0). >>>t and enter in IDLE will return - Time(10,0)"""

    def __repr__(self):
        return 'Time({0},{1})'.format(self.hours, self.minutes)


"""That class is represent a Calendar Entry - year, month and day. In that class there is __repr__ method, __str__,
, __init__, addTask and tasks.
For example:  todo = CalendarEntry(2017,1,20) - the year is 2017, month is 1, and day is 20."""


class CalendarEntry(object):
    dictTasks = {}

    """The constructor"""

    def __init__(self, year=0, month=0, day=0):
        self.Date = Date(year, month, day)

    """The __str__ method is return a string that represent the calendar entry with the tasks that there is in a 
    specific date, that are organized by a time order.
    For example: todo = CalendarEntry(2017,1,20). (I assumed that there is a tasks that was added already)
    print(todo) will return:
    Todo list for 20th of January, 2017:
    1. 10:00-13:00 - PPL lecture
    2. 14:00-16:00 – PPL homework#4
    """

    def __str__(self):
        self.dictTasks = {k: v for k, v in sorted(self.dictTasks.items(), key=lambda item: item[0])}
        listItems = list(self.dictTasks.items())
        stringTasks = ''
        strDate = 'Todo list for ' + self.Date.__str__() + ':'
        for i in range(1, len(listItems) + 1):
            stringTasks = stringTasks + str(i) + '.' + listItems[i - 1][0][0] + '-' + listItems[i - 1][0][1] + '-' + \
                          listItems[i - 1][
                              1] + '\n'
        return strDate + '\n' + stringTasks

    """The __repr__ method is return a string that represent the CalendarEntry in a format of object as it represent in OOP.
    For example: todo = CalendarEntry(2017,1,20). >>>todo and enter in IDLE will return - CalendarEntry(2017,1,20)"""

    def __repr__(self):
        return 'CalendarEntry({0},{1},{2})'.format(self.Date.year, self.Date.month, self.Date.day)

    """addTask is a function that adding a task that sent to it with start time and and time to the dictionary of the 
    tasks. For example: if todo = CalendarEntry(2017,1,20). so - todo.addTask(“PPL homework#4”, Time(14,0), Time(16,
    0)) - will add to the dictionary: {('14:00','16:00'): 'PPL homework#4'} """

    def addTask(self, task, start, end):
        self.dictTasks[(start.__str__(), end.__str__())] = task

    """tasks is a function that return the dictionary of the tasks.
    For example: if todo = CalendarEntry(2017,1,20)
    in the IDLE >>>todo.tasks will return {(’14:00’, ’16:00’): 'PPL homework#4', (‘10:00’, ’13:00’): 'PPL lecture'}"""

    def tasks(self):
        return self.dictTasks


today = Date(2017, 1, 20)
print(today.__repr__())
print(today.year)
print(today)
todo = CalendarEntry(2017, 1, 20)
t = Time(10, 0)
print(str(t))
todo.addTask('PPL lecture', t, Time(13, 0))
todo.addTask('PPL homework#4', Time(14, 0), Time(16, 0))
print(todo.tasks())
print(todo.__str__())


# part 1 - ex 2

def make_class(attrs, base=None):
    """Return a new class (a dispatch dictionary) with given class attributes"""

    # print(attrs)
    # Getter: class attribute (looks in this class, then base)
    def get(name):
        if name in attrs:
            return attrs[name]
        elif base:
            return base['get'](name)

    # Setter: class attribute (always sets in this class)
    def set(name, value):
        attrs[name] = value

    # Return a new initialized objec'aaa': 5.5t instance (a dispatch dictionary)
    def new(*args):
        # instance attributes (hides encapsulating function's attrs)
        attrs = {}

        # Getter: instance attribute (looks in object, then class (binds self if callable))
        def get(name):
            if name in attrs:
                return attrs[name]
            else:
                value = cls['get'](name)
                if callable(value):
                    return lambda *args: value(obj, *args)
                else:
                    return value

        # Setter: instance attribute (always sets in object)
        def set(name, value):
            attrs[name] = value

        # instance dictionary
        obj = {'get': get, 'set': set}

        # calls constructor if present
        init = get('__init__')
        if init: init(*args)

        return obj

    # class dictionary
    cls = {'get': get, 'set': set, 'new': new}
    return cls


"""This function is a API of the Date class that I built above
For example: >>>Date = make_date_class()
>>>today = Date['new'](2017, 1, 20)
so the year is 2017, month is 1 and the day is 20.
"""


def make_date_class():
    """The constructor"""

    def __init__(self, year=0, month=0, day=0):
        self['set']('year', year)
        self['set']('month', month)
        self['set']('day', day)

    return make_class(locals())


"""This function is a API of the Time class that I built above
For example: >>>Time = make_date_class()
>>>t = Time['new'](10, 0)
so the hour is 10 and the minutes is 0.
"""


def make_time_class():
    """The constructor"""

    def __init__(self, hours=0, minutes=0):
        self['set']('hours', hours)
        self['set']('minutes', minutes)

    """The __str__ method is return a string that represent the Time in a format the humans usually read a time.
    For example: t = Time['new'](10,0). t['get']('__str__')() will return - '10:00' """

    def __str__(self):
        return '{0:02d}:{1:02d}'.format(self['get']('hours'), self['get']('minutes'))

    return make_class(locals())


"""This function is a API of the CalendarEntry class that I built above
For example: >>> CalendarEntry = make_calentry_class()
>>> todo = CalendarEntry[‘new’](2017, 1, 20)
so the year is 2017, the month is 1 and the day is 20.
"""


def make_calentry_class():
    """The constructor"""

    def __init__(self, year=0, month=0, day=0):
        Date = make_date_class()
        Date['new'](year, month, day)
        self['set']('tasks', {})

    """addTask is a function that adding a task that sent to it with start time and and time to the dictionary of the 
    tasks.
    For example: if  todo = CalendarEntry[‘new’](2017, 1, 20). so - todo[‘get’](‘addTask’)(“PPL lecture”, 
    Time['new'](11,0), Time['new'](13,0)) - will add to the dictionary: {('11:00','13:00'): 'PPL homework#4'} """

    def addTask(self, strTask, start, end):
        (self['get']('tasks'))[(start['get']('__str__')(), end['get']('__str__')())] = strTask

    """tasks is a function that return the dictionary of the tasks. For example: if todo = CalendarEntry[‘new’](2017, 
    1, 20) in the IDLE >>>todo[‘get’](‘tasks’) will return {(’14:00’, ’16:00’): 'PPL homework#4', (‘10:00’, 
    ’13:00’): 'PPL lecture'} """

    def tasks(self):
        return self['get']('tasks')

    return make_class(locals())


Date = make_date_class()
today = Date['new'](2017, 1, 20)
print(today['get']('year'))
CalendarEntry = make_calentry_class()
todo = CalendarEntry['new'](2017, 1, 20)
Time = make_time_class()
t = Time['new'](10, 0)
print(t['get']('__str__')())
todo['get']('addTask')("PPl lecture", t, Time['new'](13, 0))
todo['get']('addTask')("PPl homework#4", Time['new'](14, 0), Time['new'](16, 0))
print(todo['get']('tasks'))

# part 2 - ex 3

rates = {('dollar', 'nis'): 3.82, ('euro', 'nis'): 4.07}

"""This class is represent the coin Shekel. In that class there is some of methods as:
__init__, amount, __add__, __repr__ and __str__.
For example: s = Shekel(50), so sum is 50 nis."""


class Shekel(object):
    """The constructor"""

    def __init__(self, sum):
        self.sum = sum

    """This function is return the amount
    For example: if s = Shekel(50), so s.amount() is return 50."""

    def amount(self):
        return self.sum

    """This function is represent the operator '+'. if I want to adding to different coins,
    this method return the sum in nis. 
    For example: if s=Shekel(50) and d=Dollar(50). so s+d will convert the dollars to nis and return the sum in nis."""

    def __add__(self, other):
        return self.sum + other.amount()

    """The __repr__ method is return a string that represent the Shekel in a format of object as it represent in OOP.
    For example: s=Shekel(50). >>>s and enter in IDLE will return - Shekel(50)"""

    def __repr__(self):
        return 'Shekel({0})'.format(self.sum)

    """The __str__ method is return a string that represent the Shekel in a format the humans usually read a coin.
    For example: s = Shekel(50). print(s) will return - '50.0nis' """

    def __str__(self):
        return '{0:.1f}nis'.format(self.sum)


"""This class is represent the coin Dollar. In that class there is some of methods as:
__init__, amount, __add__, __repr__ and __str__.
For example: d = Dollar(50), so sum is 50 $."""


class Dollar(object):
    """The constructor"""

    def __init__(self, sum):
        self.sum = sum

    """This function is return the amount in nis.
    For example: if d = Dollar(50), so d.amount() is convert the 50 dollars to nis and return 191.0"""

    def amount(self):
        return rates[('dollar', 'nis')] * self.sum

    """This function is represent the operator '+'. if I want to adding to different coins,
    this method return the sum in nis. 
    For example: if s=Shekel(50) and d=Dollar(50). so s+d will convert the dollars to nis and return the sum in nis."""

    def __add__(self, other):
        return self.amount() + other.amount()

    """The __repr__ method is return a string that represent the Dollar in a format of object as it represent in OOP.
    For example: d=Dollar(50). >>>d and enter in IDLE will return - Dollar(50)"""

    def __repr__(self):
        return 'Dollar({0})'.format(self.sum)

    """The __str__ method is return a string that represent the Dollar in a format the humans usually read a coin.
    For example: d = Dollar(50). print(d) will return - '50.0$' """

    def __str__(self):
        return '{0:.1f}$'.format(self.sum)


"""This class is represent the coin Euro. In that class there is some of methods as:
__init__, amount, __add__, __repr__ and __str__.
For example: e = Euro(50), so sum is 50 €."""


class Euro(object):
    """The constructor"""

    def __init__(self, sum):
        self.sum = sum

    """This function is return the amount in nis.
    For example: if e = Euro(50), so e.amount() is convert the 50  Euros to nis and return 203.5"""

    def amount(self):
        return rates[('euro', 'nis')] * self.sum

    """This function is represent the operator '+'. if I want to adding to different coins,
    this method return the sum in nis. 
    For example: if s=Shekel(50) and e=Euro(50). so s+e will convert the euros to nis and return the sum in nis."""

    def __add__(self, other):
        return self.amount() + other.amount()

    """The __repr__ method is return a string that represent the Euro in a format of object as it represent in OOP.
    For example: e=Euro(50). >>>e and enter in IDLE will return - Euro(50)"""

    def __repr__(self):
        return 'Euro({0})'.format(self.sum)

    """The __str__ method is return a string that represent the Euro in a format the humans usually read a coin.
    For example: e = Euro(50). print(e) will return - '50.0€' """

    def __str__(self):
        return '{0:.1f}€'.format(self.sum)


"""This function is return the sum between to coins in nis.
For example is s=Shekel(50) and d=Dollar(50) so add(s,d) return the sum between them in nis."""


def add(self, other):
    return self.amount() + other.amount()


s = Shekel(50)
d = Dollar(50)
e = Euro(50)
print(d.amount())
print(e.amount())
print(d + s)
print(add(d, e))
z = eval(repr(d))
print(z)
print(s)
print(e)

# part 2 - ex 4

"""This function is return the value in the "type_tag.tags" dictionary of the type Class that sent to this func.
For example: if x = Shekel the func return 'nis'."""


def type_tag(x):
    return type_tag.tags[type(x)]


type_tag.tags = {Shekel: 'nis', Dollar: 'dollar', Euro: 'euro'}

"""This function is get a string that represent a operator and to objects that represent coins. The function return 
the value of the key in the apply.implementations dictionary. For example: if I send to this function 'add', 
Shekel(50), Dollar(50) so the function return the value of that key: ('add', ('nis', 'dollar')). The value is a 
function that add the the right object to the left object and return the result by the format of the left object. """


def apply(operator_name, x, y):
    tags = (type_tag(x), type_tag(y))
    key = (operator_name, tags)
    return apply.implementations[key](x, y)


"""This function add shekels to dollars and return the result by the left object - Shekel.
For example: o1=Shekel(50) and o2=Dollar(20), so the func return Shekel(126.4)"""


def add_shekel_and_dollar(o1, o2):
    new = round(o1.sum + o2.amount(), 2)
    return Shekel(new)


"""This function add shekels to Euros and return the result by the left object - Shekel.
For example: o1=Shekel(50) and o2=Euro(20), so the func return Shekel(131.4)"""


def add_shekel_and_euro(o1, o2):
    new = round(o1.sum + o2.amount(), 2)
    return Shekel(new)


"""This function add shekels to shekels and return the result by the left object - Shekel.
For example: o1=Shekel(50) and o2=Shekel(20), so the func return Shekel(70)"""


def add_shekel_and_shekel(o1, o2):
    new = round(o1.sum + o2.sum, 2)
    return Shekel(new)


"""This function add dollars to shekels and return the result by the left object - dollar.
For example: o1=Dollar(50) and o2=Shekel(20), so the func return Dollar(55.24)"""


def add_dollar_and_shekel(o1, o2):
    new = round(o1.sum + (o2.sum / rates[('dollar', 'nis')]), 2)
    return Dollar(new)


"""This function add dollars to euros and return the result by the left object - dollar.
For example: o1=Dollar(50) and o2=Euro(20), so the func return Dollar(71.2)"""


def add_dollar_and_euro(o1, o2):
    new = round(o1.sum + (o2.sum * rates[('euro', 'dollar')]), 2)
    return Dollar(new)


"""This function add dollars to dollars and return the result by the left object - dollar.
For example: o1=Dollar(50) and o2=Dollar(20), so the func return Dollar(70)"""


def add_dollar_and_dollar(o1, o2):
    new = round(o1.sum + o2.sum, 2)
    return Dollar(new)


"""This function add euros to shekels and return the result by the left object - euro.
For example: o1=Euro(50) and o2=Shekel(20), so the func return Euro(54.9)"""


def add_euro_and_shekel(o1, o2):
    new = round(o1.sum + (o2.sum / rates[('euro', 'nis')]), 2)
    return Euro(new)


"""This function add euros to dollars and return the result by the left object - euro.
For example: o1=Euro(50) and o2=Dollar(20), so the func return Euro(68.87)"""


def add_euro_and_dollar(o1, o2):
    new = round(o1.sum + (o2.sum / rates[('euro', 'dollar')]), 2)
    return Euro(new)


"""This function add euros to euros and return the result by the left object - euro.
For example: o1=Euro(50) and o2=Euro(20), so the func return Euro(70)"""


def add_euro_and_euro(o1, o2):
    new = round(o1.sum + o2.sum, 2)
    return Euro(new)


"""This function sub shekels and dollars and return the result by the left object - Shekel.
For example: o1=Shekel(100) and o2=Dollar(20), so the func return Shekel(23.6)"""


def sub_shekel_and_dollar(o1, o2):
    new = round(o1.sum - o2.amount(), 2)
    return Shekel(new)


"""This function sub shekels and euros and return the result by the left object - Shekel.
For example: o1=Shekel(100) and o2=Euro(20), so the func return Shekel(18.6)"""


def sub_shekel_and_euro(o1, o2):
    new = round(o1.sum - o2.amount(), 2)
    return Shekel(new)


"""This function sub shekels and shekels and return the result by the left object - Shekel.
For example: o1=Shekel(100) and o2=Shekel(20), so the func return Shekel(80)"""


def sub_shekel_and_shekel(o1, o2):
    new = round(o1.sum - o2.sum, 2)
    return Shekel(new)


"""This function sub dollars and shekels and return the result by the left object - dollar.
For example: o1=Dollar(50) and o2=Shekel(20), so the func return Dollar(44.76)"""


def sub_dollar_and_shekel(o1, o2):
    new = round(o1.sum - (o2.sum / rates[('dollar', 'nis')]), 2)
    return Dollar(new)


"""This function sub dollars and euros and return the result by the left object - dollar.
For example: o1=Dollar(50) and o2=Euro(20), so the func return Dollar(28.8)"""


def sub_dollar_and_euro(o1, o2):
    new = round(o1.sum - (o2.sum * rates[('euro', 'dollar')]), 2)
    return Dollar(new)


"""This function sub dollars and dollars and return the result by the left object - dollar.
For example: o1=Dollar(50) and o2=Dollar(20), so the func return Dollar(30)"""


def sub_dollar_and_dollar(o1, o2):
    new = round(o1.sum - o2.sum, 2)
    return Dollar(new)


"""This function sub euro and shekels and return the result by the left object - euro.
For example: o1=Euro(50) and o2=Shekel(20), so the func return Euro(45.1)"""


def sub_euro_and_shekel(o1, o2):
    new = round(o1.sum - (o2.sum / rates[('euro', 'nis')]), 2)
    return Euro(new)


"""This function sub euro and dollars and return the result by the left object - euro.
For example: o1=Euro(50) and o2=Dollar(20), so the func return Euro(31.13)"""


def sub_euro_and_dollar(o1, o2):
    new = round(o1.sum - (o2.sum / rates[('euro', 'dollar')]), 2)
    return Euro(new)


"""This function sub euros and euros and return the result by the left object - euro.
For example: o1=Euro(50) and o2=Euro(20), so the func return Euro(30)"""


def sub_euro_and_euro(o1, o2):
    new = round(o1.sum - o2.sum, 2)
    return Euro(new)


apply.implementations = {('add', ('nis', 'dollar')): add_shekel_and_dollar,
                         ('add', ('nis', 'euro')): add_shekel_and_euro,
                         ('add', ('nis', 'nis')): add_shekel_and_shekel,
                         ('add', ('dollar', 'nis')): add_dollar_and_shekel,
                         ('add', ('dollar', 'euro')): add_dollar_and_euro,
                         ('add', ('dollar', 'dollar')): add_dollar_and_dollar,
                         ('add', ('euro', 'nis')): add_euro_and_shekel,
                         ('add', ('euro', 'dollar')): add_euro_and_dollar,
                         ('add', ('euro', 'euro')): add_euro_and_euro,
                         ('sub', ('nis', 'dollar')): sub_shekel_and_dollar,
                         ('sub', ('nis', 'euro')): sub_shekel_and_euro,
                         ('sub', ('nis', 'nis')): sub_shekel_and_shekel,
                         ('sub', ('dollar', 'nis')): sub_dollar_and_shekel,
                         ('sub', ('dollar', 'euro')): sub_dollar_and_euro,
                         ('sub', ('dollar', 'dollar')): sub_dollar_and_dollar,
                         ('sub', ('euro', 'nis')): sub_euro_and_shekel,
                         ('sub', ('euro', 'dollar')): sub_euro_and_dollar,
                         ('sub', ('euro', 'euro')): sub_euro_and_euro}

print(repr(apply('add', Shekel(50), Dollar(20))))
rates[('euro', 'dollar')] = 1.06
print(repr(apply('add', Dollar(50), Euro(20))))
print(repr(apply('sub', Dollar(50), Euro(20))))

# part 2 - ex 5


"""This function is convert dollar to shekel and return the object "Shekel" with the amount after converting to nis.
For example: if x=Dollar(50), so the func return Shekel(191)"""


def dollar_to_shekel(x):
    new = round(x.amount(), 2)
    return Shekel(new)


"""This function is convert euro to shekel and return the object "Shekel" with the amount after converting to nis.
For example: if x=Euro(50), so the func return Shekel(203.5)"""


def euro_to_shekel(x):
    new = round(x.amount(), 2)
    return Shekel(new)


coercions = {('dollar', 'nis'): dollar_to_shekel,
             ('euro', 'nis'): euro_to_shekel,
             ('dollar', 'euro'): lambda x: euro_to_shekel(x) if type(x) == Euro else dollar_to_shekel(x)}

"""This function convert the relevant type of coin to Shekel and then return the value of the key that in the 
coerce_apply.implementations dictionary, and the value is actually a function that add / sub between the objects For 
example: if I send to the function 'add' and 'Shekel(50)' and 'Dollar(20) the function convert the Dollar(20) to 
Shekel(76.4) and return the value of the key ['add', 'nis'] with (Shekel(50), Shekel(76.4)) """


def coerce_apply(operator_name, x, y):
    tx, ty = type_tag(x), type_tag(y)
    if tx != ty:
        if (tx, ty) in coercions:
            tx, x = ty, coercions[(tx, ty)](x)
        elif (ty, tx) in coercions:
            ty, y = tx, coercions[(ty, tx)](y)
        else:
            return 'No coercion possible.'
    key = (operator_name, tx)
    return coerce_apply.implementations[key](x, y)


"""This function is add 2 objects to Shekels. if 1 of the objects are different from Shekel it convert to Shekel.
And then return the result of the adding between the 2.
For example: if x=Shekel(50) and y=Shekel(76.4) the function return Shekel(126.4)"""


def add_shekels(x, y):
    if type(y) == Euro:
        newY = y.amount()
        newSum = x.sum + newY
    elif type(y) == Dollar:
        newY = y.amount()
        newSum = x.sum + newY
    else:
        newSum = x.sum + y.sum
    return Shekel(newSum)


"""This function is sub 2 objects to Shekels. if 1 of the objects are different from Shekel it convert to Shekel.
And then return the result of the difference between the 2.
For example: if x=Shekel(100) and y=Shekel(50) the function return Shekel(50)"""


def sub_shekels(x, y):
    if type(y) == Euro:
        newY = y.amount()
        newDiff = x.sum - newY
    elif type(y) == Dollar:
        newY = y.amount()
        newDiff = x.sum - newY
    else:
        newDiff = x.sum - y.sum
    return Shekel(newDiff)


coerce_apply.implementations = {('add', 'nis'): add_shekels,
                                ('add', 'dollar'): add_shekels,
                                ('add', 'euro'): add_shekels,
                                ('sub', 'nis'): sub_shekels,
                                ('sub', 'dollar'): sub_shekels,
                                ('sub', 'euro'): sub_shekels}

print(repr(coercions[('dollar', 'nis')](Dollar(50))))
print(repr(coerce_apply('add', Shekel(50), Dollar(20))))
print(repr(coerce_apply('add', Dollar(50), Euro(20))))
print(repr(coerce_apply('sub', Dollar(50), Euro(20))))

# part 3 - ex 6
# ex 5 from HW3

"""This function is execute the principle of API by use the method of dispatch dictionary.
The function get a pay per hour, and a number of places that remaining for every type of parking.
All the examples I wrote for every function that are in that function"""


def parking(payPerHour, numOfReg, numOfPrior, numOfVIP):
    """Here is an try to catch an exception if the payPerHour is a negative number. if it is, so it raise a Value Error,
    and throw an exception that print "the price value is bad", and return.
    For example: if payPerHour = -10, the exception will throw."""
    try:
        if payPerHour < 0:
            raise ValueError
    except ValueError as val:
        print("the price value is bad")
        return
    """Here is an try to catch an exception if the number of the empty places in every parking type is not a at least 1. if 
    it is, so it raise a Value Error, and throw an exception that print "parking places error" and return.
    For example: if numOfReg = 0, the exception will throw. """
    try:
        if numOfReg <= 0:
            raise ValueError
        if numOfPrior <= 0:
            raise ValueError
        if numOfVIP <= 0:
            raise ValueError
    except ValueError as val:
        print("parking places error")
        return
    listOfCars = []

    """This function is get a num of car and type of the parking ,and insert to a list another lost every time this 
    function is execute. every sub list is contain a num of car, type of parking and num of hours in the parking.
    if the number of places of the type of parking is zero, the function return that the parking is full.
    For example:  park1=parking(10,3,3,3),  park1['start_parking'](222,'Regular'), so now the list is:
    [ [222, Regular, 1] ]."""

    def start_parking(numOfCar, typeOfParking):
        """Here is an try to catch an exception if the type of the numOfCar is not an int. if it is, so it raise a Type Error,
        and throw an exception that print "incorrect car number". and return
        For example: if numOfCar = 'aaa', the exception will throw."""
        try:
            if type(numOfCar) != int:
                raise TypeError
        except TypeError as typ:
            print("incorrect car number")
            return
        """Here is an try to catch an exception if the type of the parking is not a 'VIP', 'Regular'' 'Priority'. if 
        its not, so it raise a Type Error, and throw an exception that print (typeOfParking, "is incorrect parking 
        type", and return.
        For example: if typeOfParking = 'VIP1', the exception will throw. """
        try:
            if (typeOfParking != 'VIP') and (typeOfParking != 'Regular') and (typeOfParking != 'Priority'):
                raise TypeError
        except TypeError as typ:
            print(typeOfParking, "is incorrect parking type")

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

        def next():
            nonlocal index
            """Here is an try to catch an exception if the index is bigger than the len of the list of cars . if it 
            is, so it raise an Index Error, and throw an exception that print "no car" to every extra index from the 
            len. and return
            For example: if len is 4 and index is 6, so "no car" will be printed 2 times."""
            try:
                if index >= len(listOfCars):
                    raise IndexError
                print("car: ", listOfCars[index][0], ",parking type: ", listOfCars[index][1], ",parking time: ",
                      listOfCars[index][2])
                index += 1
            except IndexError as i:
                print("no car")
                return

        return {'next': next}

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


park1 = parking(-10, 3, 3, 3)
park1 = parking(10, 0, 3, 3)
park1
park1 = parking(10, 3, 3, 3)
park1['start_parking']('aaa', 'VIP')
park1['start_parking'](223, 'VIP1')
park1['start_parking'](222, 'Regular')
park1['start_parking'](223, 'Regular')
park1['next_time']()
park1['start_parking'](224, 'Regular')
park1['start_parking'](225, 'VIP')
prn = park1['print_list']()
print(prn)
for _ in range(6):
    prn['next']()

# part 4 - ex 7
"""This class Expr is represent an arithmetic expression that constitute by 1 operator and 2 operands.
For example: Expr('add',1,2) the operator is 'add' the operand1 is 1 and the operand2 is 2."""


class Expr(object):
    """The constructor"""

    def __init__(self, operator, operand1, operand2):
        self.operator = operator
        self.operand1 = operand1
        self.operand2 = operand2

    """The __repr__ method is return a string that represent the Expr in a format of object as it represent in OOP.
    For example: exp = Expr('add',1,2). >>>exp and enter in IDLE will return - Expr('add',1,2)"""

    def __repr__(self):
        return 'Expr({0},{1},{2})'.format(repr(self.operator), repr(self.operand1), repr(self.operand2))


# part 4 - ex 8
"""This function is a recursive func that build an arithmetic expression that could be complex.
For example: >>> exp = build_expr_tree((‘add’, (‘mul’, 2, 3), 10))
>>> exp
that function return - Expr(‘add’, Expr(‘mul’,3,2),10)"""


def build_expr_tree(tupleExpr):
    if type(tupleExpr) != tuple:
        return tupleExpr
    return Expr(tupleExpr[0], build_expr_tree(tupleExpr[1]), build_expr_tree(tupleExpr[2]))


exp = build_expr_tree(('add', ('mul', 2, 3), 10))
print(exp)

# part 5 - ex 9
"""Calculator

An interpreter for a calculator language using prefix-order call syntax.
Operator expressions must be simple operator names or symbols.  Operand
expressions are separated by commas.

Examples:
    calc> mul(1, 2, 3)
    6
    calc> add()
    0
    calc> add(2, div(4, 8))
    2.5
    calc> add
    SyntaxError: expected ( after add
    calc> div(5)
    TypeError: div requires exactly 2 arguments
    calc> div(1, 0)
    ZeroDivisionError: division by zero
    calc> ^DCalculation completed.
"""

from functools import reduce
from operator import mul, add


def read_eval_print_loop():
    """Run a read-eval-print loop for calculator."""
    while True:
        try:
            expression_tree = calc_parse(input('calc> '))
            print(calc_eval(expression_tree))
        except (SyntaxError, TypeError, ZeroDivisionError) as err:
            print(type(err).__name__ + ':', err)
        except (KeyboardInterrupt, EOFError):  # <Control>-D, etc. <ctrl-C>
            print('Calculation completed.')
            return


# Eval & Apply

class Exp(object):
    """A call expression in Calculator.

    >>> Exp('add', [1, 2])
    Exp('add', [1, 2])
    >>> str(Exp('add', [1, Exp('mul', [2, 3])]))
    'add(1, mul(2, 3))'
    """

    def __init__(self, operator, operands):
        self.operator = operator
        self.operands = operands

    def __repr__(self):
        return 'Exp({0}, {1})'.format(repr(self.operator), repr(self.operands))

    def __str__(self):
        operand_strs = ', '.join(map(str, self.operands))
        return '{0}({1})'.format(self.operator, operand_strs)


def calc_eval(exp):
    """Evaluate a Calculator expression.

    >>> calc_eval(Exp('add', [2, Exp('mul', [4, 6])]))
    26
    """
    if type(exp) in (int, float):
        return exp
    if type(exp) == Exp:
        arguments = list(map(calc_eval, exp.operands))
        return calc_apply(exp.operator, arguments)


def calc_apply(operator, args):
    """Apply the named operator to a list of args.

    >>> calc_apply('+', [1, 2, 3])
    6
    >>> calc_apply('-', [10, 1, 2, 3])
    4
    >>> calc_apply('*', [])
    1
    >>> calc_apply('/', [40, 5])
    8.0
    """
    if operator in ('add', '+'):
        return sum(args)
    if operator in ('sub', '-'):
        if len(args) == 0:
            raise TypeError(operator + 'requires at least 1 argument')
        if len(args) == 1:
            return -args[0]
        return sum(args[:1] + [-arg for arg in args[1:]])
    if operator in ('mul', '*'):
        """Here I check if the operator 'mul' get more than 1 argument. if not - there is a raise of Type Error"""
        if len(args) < 1:
            raise TypeError(operator + ' requires at least 1 argument')
        return reduce(mul, args, 1)

    "Here its the adding of the 'round' functionality that get a number and get a number of digits that the user " \
    "wants to after the dot"" "

    if operator in ('round'):
        return round(args[0], args[1])
    if operator in ('div', '/'):
        if len(args) != 2:
            raise TypeError(operator + ' requires exactly 2 arguments')
        """Here I check if there is a div in Zero. if it is, it return a inf."""
        if args[1] == 0:
            return float("inf")

        numer, denom = args
        return numer / denom


# Parsing

def calc_parse(line):
    """Parse a line of calculator input and return an expression tree."""
    tokens = tokenize(line)
    expression_tree = analyze(tokens)
    if len(tokens) > 0:
        raise SyntaxError('Extra token(s): ' + ' '.join(tokens))
    return expression_tree


def tokenize(line):
    """Convert a string into a list of tokens.

    >>> tokenize('add(2, mul(4, 6))')
    ['add', '(', '2', ',', 'mul', '(', '4', ',', '6', ')', ')']
    """
    spaced = line.replace('(', ' ( ').replace(')', ' ) ').replace(',', ' , ')
    return spaced.strip().split()


known_operators = ['add', 'sub', 'mul', 'div', 'round', '+', '-', '*', '/']


def analyze(tokens):
    """Create a tree of nested lists from a sequence of tokens.

    Operand expressions can be separated by commas, spaces, or both.

    >>> analyze(tokenize('add(2, mul(4, 6))'))
    Exp('add', [2, Exp('mul', [4, 6])])
    >>> analyze(tokenize('mul(add(2, mul(4, 6)), add(3, 5))'))
    Exp('mul', [Exp('add', [2, Exp('mul', [4, 6])]), Exp('add', [3, 5])])
    """
    assert_non_empty(tokens)
    token = analyze_token(tokens.pop(0))
    if type(token) in (int, float):
        return token
    if token in known_operators:
        if len(tokens) == 0 or tokens.pop(0) != '(':
            raise SyntaxError('expected ( after ' + token)
        return Exp(token, analyze_operands(tokens))
    else:
        raise SyntaxError('unexpected ' + token)


def analyze_operands(tokens):
    """Analyze a sequence of comma-separated operands."""
    assert_non_empty(tokens)
    operands = []
    while tokens[0] != ')':
        if operands and tokens.pop(0) != ',':
            raise SyntaxError('expected ,')
        operands.append(analyze(tokens))
        assert_non_empty(tokens)
    tokens.pop(0)  # Remove )
    return operands


def assert_non_empty(tokens):
    """Raise an exception if tokens is empty."""
    if len(tokens) == 0:
        raise SyntaxError('unexpected end of line')


def analyze_token(token):
    """Return the value of token if it can be analyzed as a number, or token.

    >>> analyze_token('12')
    12
    >>> analyze_token('7.5')
    7.5
    >>> analyze_token('add')
    'add'
    """
    try:
        return int(token)
    except (TypeError, ValueError):
        try:
            return float(token)
        except (TypeError, ValueError):
            return token


def run():
    read_eval_print_loop()


run()
