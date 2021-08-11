![[Список_тем_Устное_собеседование_Часть_1_Java_SE+Threads_2021_07.pdf]]

1.1.1 History Java
Изначально назывался oak из-за дуба у главного офиса и разрабатывался Джеймсом Гослингом для программирования бытовых электронных устройств . Далее из-за нарушения прав был переименован в Java в честь кофе	С середины 1990-х годов язык стал широко использоваться для написания клиентских приложений и  серверного программного обеспечения. Тогда же определённое распространение получила технология Java-апплетов.
В веб-разработке применяется Spring Framework; для документирования используется утилита Javadoc.

1.1.2 Область применения Java
 ПРИЛОЖЕНИЯ ДЛЯ РАБОЧЕГО СТОЛА (ДЕСКТОПНЫЕ) С ГРАФИЧЕСКИМ ИНТЕРФЕЙСОМ
 МОБИЛЬНЫЕ ПРИЛОЖЕНИЯ
 ВЕБ-ПРИЛОЖЕНИЯ
 ВСТРОЕННЫЕ СИСТЕМЫ(На некоторых устройствах, таких как SIM-карты, проигрыватели дисков Blue-ray, счетчики коммунальных услуг и телевизоры, используются встроенные технологии Java. Согласно Oracle, 100% проигрывателей Blu-ray дисков и 125 миллионов телевизионных устройств используют Java.)
 ВЕБ-СЕРВЕРЫ И СЕРВЕРЫ ПРИЛОЖЕНИЙ
 КОРПОРАТИВНЫЕ СИСТЕМЫ
 ТЕХНОЛОГИИ БОЛЬШИХ ДАННЫХ
 НАУЧНЫЕ ПРИЛОЖЕНИЯ
 1.1.3 Использование памяти. Жизненный цикл программы
 Для оптимальной работы приложения JVM делит память на область стека (stack) и область кучи (heap). Всякий раз, когда мы объявляем новые переменные, создаем объекты или вызываем новый метод, JVM выделяет память для этих операций в стеке или в куче.
 1.Стек работает по схеме LIFO (последним вошел, первым вышел). Всякий раз, когда вызывается новый метод, содержащий примитивные значения или ссылки на объекты, то на вершине стека под них выделяется блок памяти.  
Когда метод завершает выполнение, блок памяти, отведенный для его нужд, очищается, и пространство становится доступным для следующего метода.
2. Куча  
Эта область памяти используется для объектов и классов. Новые объекты всегда создаются в куче, а ссылки на них хранятся в стеке.  
Эти объекты имеют глобальный доступ и могут быть получены из любого места программы.

1.1.4 Компиляция и запуск приложения из командной строки. Работа с аргументами командной строки
## 1. Компиляция программ

Для компиляции программ из командной строки используется команда javac.

Синтаксис javac: 

```markup
javac [ключи] [исходники]
```

Чтобы получить список возможных ключей, выполните:

```markup
javac –help
```

Чтобы скомпилировать программу `MyFirstApp`, запустите компилятор, указав имя исходного файла в командной строке следующим образом:

```markup
javac MyFirstApp.java
```

Компилятор javac создаст файл `MyFirstApp.class`, содержащий версию байт-кода. 

В процессе компиляции исходного кода каждый отдельный класс помещается в собственный выходной файл, называемый по имени класса и получающий расширение `.class`. 

## 2. Компиляция с помощью -d.

По умолчанию компилятор записывает сгенерированный `_._class` в тот же каталог, где находится исходный класс `.java`. Это хорошо для маленьких проектов, но при работе на больших проектах файлы `_._java` и `_._class` должны храниться отдельно.

Ключ -d позволяет указать компилятору, куда записывать сгенерированные `_._class` файлы_._

Например, у нас есть такая структура папок:

![Структура папок фото](https://storage.googleapis.com/www.examclouds.com/introduction/javac-example.png)Следующая команда, выполненная из project1 папки сгенерирует файл `MyFirstApp.class` и запишет его в папку classes _(_предполагаем, что `MyFirstApp` не содержит оператора `package`):

```java
cd project1
javac -d classes src/MyFirstApp.java
```

Теперь давайте рассмотрим пример компиляции класса `com.company.lesson1.MyFirstApp`, находящегося в пакете `com.company.lesson1`. Имеется такая структура каталогов:

![Структура папок фото](https://storage.googleapis.com/www.examclouds.com/introduction/javac-example2.png)

Следующая команда, выполненная из src каталога, сгенерирует файл `MyFirstApp.class`, и запишет его в каталог classes/com/company/lesson1:

```java
cd myProject/src
javac -d ../classes com/company/lesson1/MyFirstApp.java
```

Если каталог classes/com/company/lesson1 не существует, то он будет создан при компиляции.

Если же не существует каталог classes, то вы получите ошибку компиляции.   

## 3. Запуск программы с помощью команды java.

Чтобы выполнить программу из командной строки, следует воспользоваться загрузчиком приложений Jаvа, который называется java.

Синтаксис java:

```markup
java [ключи] класс [аргументы]
```

Для получения списка возможных ключей, выполните:

```markup
java –help
```

Чтобы выполнить программу, передадим имя класса `MyFirstApp` (предполагаем, что `MyFirstApp` не содержит оператора `package`) в качестве аргумента командной строки:

```markup
java MyFirstApp
```

Если класс `MyFirstApp` находится в пакете, то выполняем команду:

```markup
java com.company.lesson1.MyFirstApp
```

Стоит заметить, что при запуске программы указывается только один класс без расширения `.class`.
 
 1.1.5 Консоль. Простейшие примеры
 
 public Main{
 	public static void main(String[] args){
		System.out.println("Hello world");
	}
 }
 
1.2.1 Примитивные типы. Размер типа данных
byte , boolean - 1 байт
short , char - 2 байта
int , float - 4 байта 
double , long - 8 байта

1.2.2 Объявление переменных. Зарезервированные слова. Литералы
Чтобы объявить переменную нужно написать тип + название переменной

double number;

Всего 53 зарезервированных (специальных) слов
Следующий список содержит все ключевые слова:

1.  abstract
2.  assert
3.  boolean
4.  break
5.  byte
6.  case
7.  catch
8.  char
9.  class
10.  const
11.  continue
12.  default
13.  do
14.  double
15.  else
16.  enum
17.  exports
18.  extends
19.  final
20.  finally
21.  float
22.  for
23.  goto
24.  if
25.  impements
26.  import
27.  instanceof
28.  int
29.  interface
30.  long
31.  module
32.  native
33.  new
34.  package
35.  private
36.  protected
37.  public
38.  requires
39.  return
40.  short
41.  static
42.  strictfp
43.  super
44.  switch
45.  synchronized
46.  this
47.  throw
48.  throws
49.  transient
50.  try
51.  void
52.  volatile
53.  while

Литералы это фиксированное значение ,допустим 2 или "привет".

1.2.3 Преобразование типов
Когда в одной операции вовлечены данные разных типов, не всегда необходимо использовать операцию преобразования типов. Некоторые виды преобразований выполняются неявно, автоматически.
Есть правила , по которым можно преобразовать 1 тип в другой
Допустим int в long можно , а наоборот - нет . Это зависит от диапазона значений каждой из переменных.
пример 

int a = 4;
long b = (long)a;


1.2.4 Классы-оболочки. Big-классы. Упаковка/распаковка
Класс называется "оболочкой" потому, что он, по сути, копирует то, что уже существует, но добавляет новые возможности для работы с привычными типами.
Для каждого примитивного типа существует класс оболочка : 
Integer , Byte , Double , Boolean , Character , Short , Long , Float

1.2.5 Статический импорт
Импорт статических методов может быть полезным , а может.наоборот, 
либо замедлить программу ,либо сделать ее нечитаемой . Он можео быть полезен тогда и только тогда,когда требуется постоянное использование статических членов одного класса из одного или двух других классов.