a.) Design Approach:
I used simple concurrent access provided by Java util package. It provides ConcurrentLinkedQueue which can be accessed by various thread by time sharing and updated.
Its synchrnized and the java class accessing just need to implement runnable interface.
Files are uploaded on github under folder name: RandomisedPrimeChecker need to run this java file inturn it will call Randomizer.java and Prime.java
The RandomizedPrime class calls the randomizer thread and prime thread by passing the input
and output queue. Inputqueue: integer concurrent linked queue
outputQueue: concurrent linked queue holding two objects num as well as its Boolean result.
Randomizer thread generates the number and adds it to the input queue and checkes outputqueue
for any result for printing.
Prime thread checks input queue for input and calls the isPrime(int num) method and add the
output to output queue.
The sample run works for 10 numbers and prime number within range 0 to 100 which can be
alter by asking user to give range before starting the threads and option to end the thread.


b.) Output after running RandomisedPrimeChecker

Sample run for Prime Checker:
Prime checker started
Randomizer started
Added to input queue from randomizer:61
Added to input queue from randomizer:11
 Integer: 61 isPrime: true
 Integer: 11 isPrime: true
Added to input queue from randomizer:63
Added to input queue from randomizer:31
 Integer: 63 isPrime: false
 Integer: 31 isPrime: true
Added to input queue from randomizer:34
 Integer: 34 isPrime: false
Added to input queue from randomizer:9
Added to input queue from randomizer:30
 Integer: 9 isPrime: true
 Integer: 30 isPrime: false
Added to input queue from randomizer:84
 Integer: 84 isPrime: false
Added to input queue from randomizer:33

Part1-section2
1)	Reverse of a string without reverse method.---- code in ReverseOfString.java
2)	Find a palindrome ----- code in Palindrome.java
2)      Please create a class for the below and send it,
We have a table which has 4 columns as id, name, phone and address.
You need to have a method which will return me the data.
Create a method where you can hard code the data and print the details.
The main aim of above example is how you use data structure.
--- Code in Contact.java has diclaration and execute the class ContactClass.java

Part 2
Representative Questions – Please write your answers with an example for
each questions.
- How do you design an application with JMS messaging?
1. JMS stands for Java Messaging System which is an API that allows user to create, send,
read and receive the messages between software components (loosely coupled) in a
system. JMS is also asynchronous so it doesn’t need to connect to receive the message as
well as reliable and there is no retransmission of messages causing duplication. The Java
EE platform, moreover, enhances the JMS API by providing support for distributed
transactions and allowing for the concurrent consumption of messages.
2. To design an enterprise level application with asynchronous JMS messaging we can go in
following manner:
We make use of the references to the interfaces defined in the JMS package.
JMS defines a generic view of messaging that maps onto the transport or connectors. The
application that uses JMS should makes use of following interfaces:
Connection: Provides access to the underlying transport, and is used to create Sessions.
Session: Provides a context for producing and consuming the messages, using methods
MessageProducers (used to send messages) and MessageConsumers (used to receive
messages).
Destination and ConnectionFactory are for administrative purpose which are pushed on
to the cosumers to administers the application.
There are more interfaces specific to type of messaging service: point-to-point (Queue,
QueueSender, QueueBrowser, QueueReceiver) or publisher /consumer(TopicSubscriber,
TopicPublisher, TopicSessions).
Application should refer to the predefined administered objects to WebSphere
Application Server and are also bound to JNDI namespace. We can directly use them in
the application without worrying internal implementation. This allows the required
encapsulation with all the benefits of JMS API. JMS resource for Point-to-point:
Connection Factory(or QueueConnectionFactory) , Queue and resource for
Publish/Subscribe: ConnectionFactory(or TopicConnectionFactory) , Topic. A
connection factory is used to create connections from the JMS provider to the messaging
system, and encapsulates the configuration parameters needed to create connections.

3. To improve performance, the application server pools connections and sessions with the
JMS provider. We have to configure the connection and session pool properties
appropriately for our applications, otherwise we might not get the connection and session
behavior that we want. Applications must not cache JMS connections, sessions,
producers or consumers. WebSphere Application Server closes these objects when a bean
or servlet completes, and so any attempt to use a cached object will fail with
a javax.jms.IllegalStateException exception.
4. To further increase performance of the application, we should cache JMS objects that
have been looked up from JNDI. For eg: createConnection method on each instantiation
is needed, and due to pooling connections and sessions with the JMS provider, there wont
be performance impact.
5. A non-durable subscriber can only be used in the same transactional context (for
example, a global transaction or an unspecified transaction context) that existed when the
subscriber was created.
6. Whereas a durable subscription on a JMS topic enables a subscriber to receive a copy of
all messages published to that topic, even after periods of time when the subscriber is not
connected to the server. So if an application creates a durable subscription, it is added to
the runtime list that administrators can display and act on through the administrative
console. Each durable subscription is given a unique identifier, clientID##subName
where client identifier is used to associate a connection and its objects with the messages
maintained for applications. Application administrator can even use naming which are
familiar to help identify the applications. subName is the subscription name to identify
the durable subscription uniquely within a given client identifier.
For durable subscriptions created by message-driven beans, these values are set on the
JMS activation specification. For other durable subscriptions, the client identifier is set on
the JMS connection factory, and the subscription name is set by the application on
the createDurableSubscriber operation.


How do you handle exception in JMS consumers and how to you recover?

JMS consumers can handle different situation like if the message received is corrupted then it
should receive the message and put the notification back to the consumer/client. And if the
message is correct but the processing at server side cannot be finished then the message should
be left in the queue as JMS provides the feature of storing the message element in the queue even
when the server or destination is not present.
Apart from that JMS provides exception handling class JMSException. It’s the most generic way
of catching any exception related to JMS API. Several types of exceptions occur when client
being reconnected after a failover. I am catching of exception depends on whether it’s a
transacted session, type of exception and client is producer or consumer.
In case of transacted session, JMS consumer can fail either during processing transaction
statements which is called open transaction or during session.commit() which is called commit
transaction.
• In the case of a failover during an open transaction, when the client application
calls Session.commit(), the client runtime will throw
a TransactionRolledBackException and roll back the transaction causing the messages
produced are discarded which were not committed successfully before failover and
message consumed are redelivered to the consumer if not committed. A new connection
is automatically started.
• When its failover during a call to session.commit() , either it can be due to the transaction
is committed successfully and the call to Session.Commit doesn’t not return an exception,
JMS consumer doesn’t have to do anything. Or if the runtime throws a
TransactionRolledbackException and does not commit the transaction. The transaction
automatically rolled back by Message Queue runtime and the consumer must retry the
transaction. And if a JMXException is thrown means the transaction state is unknown. So
the consumer should consider it as failure and try again after a pause for some time and
calling Session.rollback() to resume the operations. But in case the transaction as
successful to handle such cases the producer should set application specific properties on
the messages it resends to signal that these might be duplicate messages. In other words,
to endure that messages are not duplicated both producers and consumers need to handle
this edge case separately which is rare occurrence.

In case of Non-transacted session JMS consumer can face various exception:
1. In consumer acknowledgement mode, calling Message.acknowledgement or
MessageConsumer.receive can raise a JMS Exception. The consumer should call
Session.recover to recover or redeliver the unacknowledged messages and then call
Message.acknowledge or MessageConsumer.receive
2. Also in auto acknowledgement mode, on getting JMS Exception, the synchronous
consumer should pause for some seconds and then resume by calling
MessageConsumer.receive to continue receiving message. Any messages failed to be
acknowledged will be redelivered using redelivered flag set to True.
3. Even in duplicate Acknowledgement mode, the synchronous consumer should wait few
seconds after getting exception and call receive. It’s possible that messages delivered and
acknowledged could be redelivered.


How do you implement LRU or MRU cache?

LRU stands for Least Recently used and MRU for Most Recently Used meaning the same. In
Java, we can create a stack data structure to store the objects which are recently used in Last
In first Out fashion. Even the function calls in recursive function also uses the same
phenomenon, the base case gets called at the end and implemented first. Even the objects
references by JVM are stored in stack as function calls are finished and returned to the old
order of processing in program.
Approach: Cache is used to store request to process it faster in future but keeping them in
least recently used order and remove least recently item used when a new one comes. So cache hits needs to be O(1). We also need a list of items to keep update of new objects at top
and discard old one at bottom. For O(1) access we use HashMap as insertion and deletion is
also O(1 ) as compared to Arrays. And we store the items in LinkedList where we can pin
point head and tail. Java has LinkedHashMap in util package to use.

How would you implement Executor Service?

1.) Executor Service is an interface in Java which allows user to execute task in parallel. It
allows user to run tasks with asynchronous execution mechanism to execute task in the
background just like tread pool execution in Java. Its present in util.concurrent package
and have implementation similar to thread pool.
2.) We can implement Executor Service for Java Thread where each task in thread can be
provided to executor service which runs this task in parallel with the native thread
processing.
In Concurrent package, there are two implementation of Executor Service;
ThreadPoolExecutor and ScheduleThreadPoolExecutor. Java provides different factory
methods to use executor:
ExecutorService e=Executors.newSingleThreadExecutor();
It contains only a single thread so all task passed to this executor will be run by this
single thread in sequential order and rest of the task waits in queue. WE use this when we
want our tasks to be performed sequentially.
ExecutorSerivce e=Executors.newFixedThreadPool(5);
It contains fixed number of threads. All task passed are executed by any of these thread.
Whereas
ExecutorService e= Executors.newScheduledThreadPool(5); allows to schedule the
process of task.
3.) ExecutorService contains methods to pass task for execution: execute ()- which takes
runnable method as parameter of task similar to thread. submit ()- takes runnable as well
as callable as parameter. Callable method parameter has return property not present with
runnable instances. And other methods like invokeAny (), invokeAll () and shutdown() to
shutdown the executor when the application doesn’t need to parallel task executor.

Describe singleton design pattern – how would you implement?

Singleton pattern is one of the design paradigm in Java. It comes as the creational design
patterns as its used mostly for creating an object of the class but only one object of it.
This pattern has a single class which is responsible for creation of object and also makes sure
at the same time that only single object is created. The single class also provides method to
access its only object which can be directly accessed instead of instantiating the object of the
class. The class is called Singleton Class.
Implementation: We will create a class with private constructor so no other class or method
can access it outside this singleton class and create more objects. And we will create a static
instance of the class within itself which we will return by one get method() to be accesses
outside the class.
Just declaring the instance variable doesn’t stop to create the new instance by calling new
Object() even though the variable is static and stored as single copy in the memory so to stop
that we can make class singleton. We make the class as singleton class when we need to
control the instances of an object. When we share the same object state across the application
we should make that class singleton so to avoid creation of more than one object.

Describe properties of Java String.

1.) In Java, String is a class and immutable object. So for an immutable object we cannot
modify any of its attributes values. Once declared and assigned it cannot be manipulated.
2.) String is collection of characters representing a word, similar to array which stores values
of same type in contiguous fashion. Likewise, String is continuous sequence of character
ending with a null to signify the end of the string.
There are different ways to declare and initialize string data type.
Eg: String str= “Hello World”;
Whenever string Literal is encountered, the compiler stores this in memory but before it
checks in string pool memory (String intern pool in Java heap) if the same string is there
then only the reference is returned to old memory location otherwise it is stored in the
pool and reference is returned. That’s why String object is immutable so that it doesn’t
alter the value of location as multiple references are referencing to the same memory
location holding the String value.
3.) So whenever a String is needed to be altered or manipulated a lot we should use String
Buffer and Builder class. String Builder is unsynchronized and String buffer is used for
thread safe code because its synchronized.
4.) Also we can use new Constructor to declare the String. Eg: String s= new String(“Hello
World”);
The String class provides the various constructor to create String from char arrays, etc.
Whenever new constructor is used it doesn’t store it in String pool and create the new
string object which is immutable. So if we compare the object referencing the same
location in memory using “==” will gives us false as it creates new object references
pointing to same location when we use new constructor. We can use intern method() to
specify for the constructor to check in String pool and store it in pool if not present
already. So when we need to create a new String object holding same old value we
should use new Constructor to declare it.
5.) Java String class provides various inbuild method for manipulations of string. Like
String.length(); gives the length of the string. We can concate two string using
str1.concat(str2);
We can also format the string using String.format(); Str1.equals(str2); checks if two
string references hold same value or not. Whereas Str1== Str2; checks if two references
are equal or not. Str1= str2; is valid and it assign the str1 reference to point to where str2
is referencing. Various other method charAt(), compareTo(),copy(),indexOf() provides
other functionalities.