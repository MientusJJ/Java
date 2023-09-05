## 01 introduction
four easy exercises for the start of programming in Java
1. Hello world from console
2. Finding roots in quadratic equation
3. reverser for strings
4. making banner from string input
## 02 spreadsheet
creating simple spreadsheet with following operations:
- ADD - adding  parameters
- SUB - subtracing  parameters
- MUL - multiplicating  parameters
- DIV - dividing  parameters
- MOD - modulo reducing
If cells contains:
- **value** - you need to leave it
- **$** - reference to other cell
- **=** - mathematical operation
## 03 collectons
Three exercises:
1. List merger
2. transforming string, which is in map format, into json
3. random creator for the board in battleship
## 04 gvt
The goal of this exercise was to create a very simple version consol system with following commands(commands are similar to git's ones):
- git init
- git add
- git detach
- git commit
- git checkout
- git history
- git version
Every command should print adequate output and every command should be error handled

## 05 2Dmap
using templates and generic programming create 2D map, which can handle following operations:
- V put(R rowKey, C columnKey, V value);

    
     * Gets a value from the map from given key.
     * 
     * @param rowKey row part of a key.
     * @param columnKey column part of a key.
     * @return object contained at specified key, or {@code null}, if the key does not contain an object.
     
-    V get(R rowKey, C columnKey);

    
     * Gets a value from the map from given key. If specified value does not exist, returns {@code defaultValue}.
     *
     * @param rowKey row part of a key.
     * @param columnKey column part of a key.
     * @param defaultValue value to be be returned, if specified key does not contain a value.
     * @return object contained at specified key, or {@code defaultValue}, if the key does not contain an object.
     
-    V getOrDefault(R rowKey, C columnKey, V defaultValue);

    
     * Removes a value from the map from given key.
     *
     * @param rowKey row part of a key.
     * @param columnKey column part of a key.
     * @return object contained at specified key, or {@code null}, if the key didn't contain an object.
 -   V remove(R rowKey, C columnKey);

    
     * Checks if map contains no elements.
     * @return {@code true} if map doesn't contain any values; {@code false} otherwise.
     
-    boolean isEmpty();

    
     * Checks if map contains any element.
     * @return {@code true} if map contains at least one value; {@code false} otherwise.
     
-    boolean nonEmpty();

    
     * Return number of values being stored by this map.
     * @return number of values being stored
     
-    int size();

   
     * Removes all objects from a map.
     
-    void clear();

    
     * Returns a view of mappings for specified key.
     * Result map should be immutable. Later changes to this map should not affect result map.
     *
     * @param rowKey row key to get view map for.
     * @return map with view of particular row. If there is no values associated with specified row, empty map is returned.
     
-    Map<C, V> rowView(R rowKey);

     * Returns a view of mappings for specified column.
     * Result map should be immutable. Later changes to this map should not affect returned map.
     *
     * @param columnKey column key to get view map for.
     * @return map with view of particular column. If there is no values associated with specified column, empty map is returned.
       
-    Map<R, V> columnView(C columnKey);

    
     * Checks if map contains specified value.
     * @param value value to be checked
     * @return {@code true} if map contains specified value; {@code false} otherwise.
     
-    boolean hasValue(V value);

     * Checks if map contains a value under specified key.
     * @param rowKey row key to be checked
     * @param columnKey column key to be checked
     * @return {@code true} if map contains specified key; {@code false} otherwise.
-    boolean hasKey(R rowKey, C columnKey);

     * Checks if map contains at least one value within specified row.
     * @param rowKey row to be checked
     * @return {@code true} if map at least one value within specified row; {@code false} otherwise.
-    boolean hasRow(R rowKey);

     * Checks if map contains at least one value within specified column.
     * @param columnKey column to be checked
     * @return {@code true} if map at least one value within specified column; {@code false} otherwise.

-    boolean hasColumn(C columnKey);

     * Return a view of this map as map of rows to map of columns to values.
     * Result map should be immutable. Later changes to this map should not affect returned map.
     *
     * @return map with row-based view of this map. If this map is empty, empty map should be returned.
       
-    Map<R, Map<C,V>> rowMapView();

     * Return a view of this map as map of columns to map of rows to values.
     * Result map should be immutable. Later changes to this map should not affect returned map.
     *
     * @return map with column-based view of this map. If this map is empty, empty map should be returned.
     
-    Map<C, Map<R,V>> columnMapView();

    
     * Fills target map with all key-value maps from specified row.
     *
     * @param target map to be filled
     * @param rowKey row key to get data to fill map from
     * @return this map (floating)
    
-    Map2D<R, C, V> fillMapFromRow(Map<? super C, ? super V> target, R rowKey);

     * Fills target map with all key-value maps from specified row.
     *
     * @param target map to be filled
     * @param columnKey column key to get data to fill map from
     * @return this map (floating)
-    Map2D<R, C, V> fillMapFromColumn(Map<? super R, ? super V> target, C columnKey);

    
     * Puts all content of {@code source} map to this map.
     *
     * @param source map to make a copy from
     * @return this map (floating)
     
-    Map2D<R, C, V>  putAll(Map2D<? extends R, ? extends C, ? extends V> source);

    
     * Puts all content of {@code source} map to this map under specified row.
     * Ech key of {@code source} map becomes a column part of key.
     *
     * @param source map to make a copy from
     * @param rowKey object to use as row key
     * @return this map (floating)
    
-    Map2D<R, C, V>  putAllToRow(Map<? extends C, ? extends V> source, R rowKey);

    
     * Puts all content of {@code source} map to this map under specified column.
     * Ech key of {@code source} map becomes a row part of key.
     *
     * @param source map to make a copy from
     * @param columnKey object to use as column key
     * @return this map (floating)
       
-    Map2D<R, C, V>  putAllToColumn(Map<? extends R, ? extends V> source, C columnKey);

    
     * Creates a copy of this map, with application of conversions for rows, columns and values to specified types.
     * If as result of row or column convertion result key duplicates, then appriopriate row and / or column in target map has to be merged.
     * If merge ends up in key duplication, then it's up to specific implementation which value from possible to choose.
     *
     * @param rowFunction function converting row part of key
     * @param columnFunction function converting column part of key
     * @param valueFunction function converting value
     * @param <R2> result map row key type
     * @param <C2> result map column key type
     * @param <V2> result map value type
     * @return new instance of {@code Map2D} with converted objects
    
-    <R2, C2, V2> Map2D<R2, C2, V2> copyWithConversion(
            Function<? super R, ? extends R2> rowFunction,
            Function<? super C, ? extends C2> columnFunction,
            Function<? super V, ? extends V2> valueFunction);

    
     * Creates new instance of empty Map2D with default implementation.
     *
     * @param <R> row key type
     * @param <C> column key type
     * @param <V> value type
     * @return new instance of {@code Map2D}

## 06 kindergarten
You have to prevent children from being hungry( dining philosophers extended problem)
Every child has his own "starvation time" and your goal is to keep it on enough level
Exercise to increase knowledge about thread's work in java
## 07 florida insurance
the idea of task is to use streams to manipulate data in packed file
the subtasks:
- find number of countries
- create file with all the insurances for 2012 year and their respective sum
- create file with two columns "country" and "value". "Country" should contains 10 countries with biggest growth of insurance betweeen 2011 and 2012. Names should be sorted descending, "value" should have value of the growth with precision 2 decimal places.

Every subtask should be done with only one pipeline



