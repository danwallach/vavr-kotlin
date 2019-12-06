package io.vavr.kotlin

import org.junit.Test
import java.lang.IllegalArgumentException

class CollectionsKtTest {
    @Test
    fun list() {
        val list: io.vavr.collection.List<Int> = list(1, 2, 3)
        assert(list.size() == 3)
    }

    @Test
    fun toMutableList() {
        val mutableList: List<Int> = list(1, 2, 3).toMutableList()
        assert(mutableList.size == 3)
    }

    @Test
    fun toVavrList1() {
        val vavrList: io.vavr.collection.List<Int> = listOf(1, 2, 3).toVavrList()
        assert(vavrList.size() == 3)
    }

    @Test
    fun toVavrList2() {
        val vavrList: io.vavr.collection.List<Int> = arrayOf(1, 2, 3).toVavrList()
        assert(vavrList.size() == 3)
    }

    @Test
    fun stream() {
        val stream: io.vavr.collection.Stream<Int> = stream(1, 2, 3)
        assert(stream.size() == 3)
    }

    @Test
    fun toVavrStream1() {
        val vavrStream: io.vavr.collection.Stream<Int> = listOf(1, 2, 3).toVavrStream()
        assert(vavrStream.size() == 3)
    }

    @Test
    fun toVavrStream2() {
        val vavrStream: io.vavr.collection.Stream<Int> = arrayOf(1, 2, 3).toVavrStream()
        assert(vavrStream.size() == 3)
    }

    @Test
    fun toVavrStream3() {
        val vavrStream: io.vavr.collection.Stream<Int> = generateSequence(1, { i -> i + 1 }).take(3).toVavrStream()
        assert(vavrStream.size() == 3)
    }

    @Test
    fun toVavrMap1() {
        val vavrMap: io.vavr.collection.Map<Int, Int> = hashMapOf(Pair(1, 2)).toVavrMap()
        assert(vavrMap.get(1).get() == 2)
    }

    @Test
    fun toVavrMap2() {
        assert(listOf(1 to 2).toVavrMap() == io.vavr.collection.HashMap.of(1, 2))
    }

    @Test
    fun toVavrMap3() {
        assert(emptyArray<io.vavr.Tuple2<Int, Int>>().toVavrMap() == io.vavr.collection.HashMap.empty<Int, Int>())
        assert(arrayOf(tuple(1, 2)).toVavrMap() == io.vavr.collection.HashMap.of(1, 2))
        assert(arrayOf(tuple(1, 2), tuple(3, 4)).toVavrMap() == io.vavr.collection.HashMap.of(1, 2, 3, 4))
    }

    @Test
    fun toVavrMap4() {
        assert(emptyArray<Pair<Int, Int>>().toVavrMap() == io.vavr.collection.HashMap.empty<Int, Int>())
        assert(arrayOf(1 to 2).toVavrMap() == io.vavr.collection.HashMap.of(1, 2))
        assert(arrayOf(1 to 2, 3 to 4).toVavrMap() == io.vavr.collection.HashMap.of(1, 2, 3, 4))
    }

    @Test
    fun toVavrMap5() {
        assert(hashMap(1 to 2, 3 to 4) == list(tuple(1, 2), tuple(3, 4)).toVavrMap())
    }

    @Test
    fun toMutableMap() {
        val mutableMap: Map<Int, Int> = io.vavr.collection.HashMap.of(1, 2).toMutableMap()
        assert(mutableMap[1] == 2)
    }

    @Test
    fun hashMap() {
        val vavrMap: io.vavr.collection.HashMap<Int, Int> = hashMap(Pair(1, 2))
        assert(vavrMap.get(1).get() == 2)
    }

    @Test
    fun linkedHashMap() {
        val vavrMap: io.vavr.collection.LinkedHashMap<Int, Int> = linkedHashMap(Pair(1, 2))
        assert(vavrMap.get(1).get() == 2)
    }

    @Test
    fun treeMap() {
        val vavrMap: io.vavr.collection.Map<Int, Int> = treeMap(Pair(1, 2))
        assert(vavrMap.get(1).get() == 2)
    }

    @Test
    fun toVavrSet1() {
        val vavrSet: io.vavr.collection.Set<Int> = hashSetOf(1, 2, 3).toVavrSet()
        assert(vavrSet.size() == 3)
    }

    @Test
    fun toVavrSet2() {
        assert(io.vavr.collection.HashSet.of("Hello", "World") == listOf("Hello", "World").toVavrSet())
    }

    @Test
    fun toVavrSet3() {
        assert(io.vavr.collection.HashSet.of("Hello", "World") == arrayOf("Hello", "World").toVavrSet())
    }

    @Test
    fun toMutableSet() {
        val mutableSet: Set<Int> = io.vavr.collection.HashSet.of(1, 2, 3).toMutableSet()
        assert(mutableSet.size == 3)
    }

    @Test
    fun hashSet() {
        val vavrSet: io.vavr.collection.HashSet<Int> = hashSet(1, 2, 3)
        assert(vavrSet.size() == 3)
    }

    @Test
    fun linkedHashSet() {
        val vavrSet: io.vavr.collection.LinkedHashSet<Int> = linkedHashSet(1, 2, 3)
        assert(vavrSet.size() == 3)
    }

    @Test
    fun treeSet() {
        val vavrSet: io.vavr.collection.TreeSet<Int> = treeSet(1, 2, 3)
        assert(vavrSet.size() == 3)
    }

    @Test
    fun getOrNull() {
        assert(hashMap(Pair(1, 2)).getOrNull(1) == 2)
        assert(hashMap(Pair(1, 2)).getOrNull(2) == null)
    }

    @Test
    fun mapFields() {
        val map = io.vavr.kotlin.hashMap(1 to 2, 3 to 4, 5 to 6)
        val mapKeys = map.keys
        val mapValues = map.values
        val mapEntries = map.entries

        assert(map.size == 3)
        assert(mapKeys == hashSet(1, 3, 5))
        assert(mapValues.size() == 3)
        assert(mapEntries.size() == 3)
    }

    @Test
    fun mapContains() {
        val map = io.vavr.kotlin.hashMap(1 to 2, 3 to 4, 5 to 6)
        assert(3 in map)
        assert(!(4 in map))
        assert(map.get(3) == some(4))
        assert(map.get(4) == none<Int>())
    }

    @Test
    fun mapPlusMap() {
        val kmap = hashMapOf(1 to 2, 3 to 4, 5 to 6) // kotlin
        val vmap1 = hashMap(1 to 2, 3 to 4, 5 to 6) // vavr
        val vmap2 = hashMap(7 to 8, 9 to 10) // vavr
        val result = hashMap(1 to 2, 3 to 4, 5 to 6, 7 to 8, 9 to 10) // vavr

        assert(kmap.toVavrMap() == vmap1)

        val v1k = vmap2 + kmap
        val kv1 = kmap + vmap2
        val v1v2 = vmap1 + vmap2

        assert(result == v1k)
        assert(result == kv1)
        assert(result == v1v2)

        assert(result == vmap1 + arrayOf(7 to 8, 9 to 10))
        assert(result == vmap1 + arrayOf(tuple(7, 8), tuple(9, 10)))

        assert(result == vmap1 + listOf(7 to 8, 9 to 10))
        assert(result == vmap1 + sequenceOf(7 to 8, 9 to 10))

        assert(result == vmap1 + list(tuple(7, 8), tuple(9, 10)))
        assert(result == vmap1 + stream(tuple(7, 8), tuple(9, 10)))
    }

    @Test
    fun mapMinusMap() {
        val start = hashMap(1 to 2, 3 to 4, 5 to 6, 7 to 8, 9 to 10) // vavr
        val result = hashMap(7 to 8, 9 to 10) // vavr

        assert(start - setOf(1, 3, 5) == result)
        assert(start - hashSet(1, 3, 5) == result)
        assert(start - list(1, 3, 5) == result)
        assert(start - listOf(1, 3, 5) == result)
        assert(start - arrayOf(1, 3, 5) == result)
        assert(start - 1 - 3 - 5 == result)
        assert(start - sequenceOf(1, 3, 5) == result)
    }

    @Test
    fun mapPlusPoint() {
        val vmap = hashMap(1 to 2, 3 to 4, 5 to 6)
        val vmap2 = hashMap(1 to 2, 3 to 4, 5 to 6, 7 to 8)

        val vmapPlus = vmap + tuple(7, 8)
        assert(vmap2 == vmapPlus)

        val vmapPlusPair = vmap + (7 to 8)
        assert(vmap2 == vmapPlusPair)
    }

    @Test
    fun setSizeField() {
        assert(0 == hashSet<Int>().size)
        assert(3 == hashSet(1, 3, 5).size)
    }

    @Test
    fun setUnionAndDifference() {
        val vset1 = hashSet(1, 3, 5)
        val kset1 = setOf(1, 3, 5)
        val vset2 = hashSet(1, 2, 3)
        val kset2 = setOf(1, 2, 3)
        val vunion = hashSet(1, 2, 3, 5)
        val vdiff = hashSet(5)

        assert(vset1 + vset2 == vunion)
        assert(vset1 - vset2 == vdiff)

        assert(vset1 + kset2 == vunion)
        assert(vset1 - kset2 == vdiff)

        assert(kset1 + vset2 == vunion)
        assert(kset1 - vset2 == vdiff)

        assert(vset1 + list(1, 2, 3) == vunion)
        assert(vset1 + listOf(1, 2, 3) == vunion)
        assert(vset1 + arrayOf(1, 2, 3) == vunion)
        assert(vset1 + sequenceOf(1, 2, 3) == vunion)
        assert(vset1 + 1 + 2 + 3 == vunion)

        assert(vset1 - list(1, 2, 3) == vdiff)
        assert(vset1 - listOf(1, 2, 3) == vdiff)
        assert(vset1 - arrayOf(1, 2, 3) == vdiff)
        assert(vset1 - sequenceOf(1, 2, 3) == vdiff)
        assert(vset1 - 1 - 2 - 3 == vdiff)
    }

    @Test
    fun seqPlusOperations() {
        val vseq = list(1, 2, 3)
        assert(list(0, 1, 2, 3) == 0 + vseq)
        assert(list(1, 2, 3, 4) == vseq + 4)

        val vsum = list(1, 2, 3, 4, 5, 6)
        val vseq2 = list(4, 5, 6)

        // Doesn't work. Ends up binding to Collection.plus(Iterable)
//        assert(listOf(1, 2, 3) + vseq2 == vsum)
        assert((listOf(1, 2, 3) + vseq2).toVavrList() == vsum)

        assert(arrayOf(1, 2, 3) + vseq2 == vsum)
        assert(sequenceOf(1, 2, 3) + vseq2 == vsum)
        assert(list(1, 2, 3) + vseq2 == vsum)

        assert(vseq + listOf(4, 5, 6) == vsum)
        assert(vseq + sequenceOf(4, 5, 6) == vsum)
        assert(vseq + arrayOf(4, 5, 6) == vsum)
    }

    @Test
    fun seqMinusOperations() {
        val vseq = list(1, 2, 3, 4)
        val result = list(1, 2)

        assert(vseq - list(4, 3, 7) == result)
        assert(vseq - 4 - 3 - 7 == result)
        assert(vseq - listOf(4, 3, 7) == result)
        assert(vseq - sequenceOf(4, 3, 7) == result)
        assert(vseq - arrayOf(4, 3, 7) == result)
    }

    @Test
    fun seqComponents() {
        val vseq = list(1, 2, 3, 4, 5, 6)
        val (v1, v2, v3, v4, v5) = vseq

        assert(v1 == 1)
        assert(v2 == 2)
        assert(v3 == 3)
        assert(v4 == 4)
        assert(v5 == 5)
    }

    @Test
    fun nullFilters() {
        assert(list("Hello", null, "World").filterNotNull() == list("Hello", "World"))
        assert(list("Hello", "World").requireNoNulls() == list("Hello", "World"))

        val shouldFail = Try { list("Hello", null).requireNoNulls() }
        assert(shouldFail.isFailure)
        assert(shouldFail.cause is IllegalArgumentException)
    }
}