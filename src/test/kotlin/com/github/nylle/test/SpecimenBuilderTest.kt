package com.github.nylle.test

import com.github.nylle.javafixture.Configuration.configure
import com.github.nylle.javafixture.SpecimenType
import com.github.nylle.javafixture.SpecimenType.fromClass
import com.github.nylle.test.testobjects.TestObjectGeneric
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.Optional

class SpecimenBuilderTest {

    @Test
    fun create() {
        val sut = SpecimenBuilder(com.github.nylle.javafixture.SpecimenBuilder<String>(fromClass(String::class.java), configure()))

        val result = sut.create()

        assertThat(result).isInstanceOf(String::class.java)
        assertThat(result).isNotBlank()
    }

    @Test
    fun createMany() {
        val sut = SpecimenBuilder(com.github.nylle.javafixture.SpecimenBuilder<String>(fromClass(String::class.java), configure()))

        val results = sut.createMany()
        assertThat(results).isInstanceOf(Sequence::class.java)

        val resultList = results.toList()
        assertThat(resultList).hasSize(3)
        assertThat(resultList[0]).isInstanceOf(String::class.java)
        assertThat(resultList[0]).isNotBlank()
    }

    @Test
    fun createTwo() {
        val sut = SpecimenBuilder(com.github.nylle.javafixture.SpecimenBuilder<String>(fromClass(String::class.java), configure()))

        val results = sut.createMany(2)
        assertThat(results).isInstanceOf(Sequence::class.java)

        val resultList = results.toList()
        assertThat(resultList).hasSize(2)
        assertThat(resultList[0]).isInstanceOf(String::class.java)
        assertThat(resultList[0]).isNotBlank()
    }

    @Test
    fun withConsumer() {
        val sut = SpecimenBuilder(com.github.nylle.javafixture.SpecimenBuilder<TestObjectGeneric<String, Optional<Int>>>(object : SpecimenType<TestObjectGeneric<String, Optional<Int>>>(){}, configure()))

        val result = sut.with { it.string = "bar"}.create()

        assertThat(result).isInstanceOf(TestObjectGeneric::class.java)
        assertThat(result.string).isEqualTo("bar")
    }

    @Test
    fun withField() {
        val sut = SpecimenBuilder(com.github.nylle.javafixture.SpecimenBuilder<TestObjectGeneric<String, Optional<Int>>>(object : SpecimenType<TestObjectGeneric<String, Optional<Int>>>(){}, configure()))

        val result = sut
                .with("t", "foo")
                .with("u", Optional.of(9))
                .create()

        assertThat(result).isInstanceOf(TestObjectGeneric::class.java)
        assertThat(result.t).isEqualTo("foo")
        assertThat(result.u).isPresent
        assertThat(result.u.get()).isEqualTo(9)
    }

    @Test
    fun without() {
        val sut = SpecimenBuilder(com.github.nylle.javafixture.SpecimenBuilder<TestObjectGeneric<String, Optional<Int>>>(object : SpecimenType<TestObjectGeneric<String, Optional<Int>>>(){}, configure()))

        val result = sut.without("t").create()

        assertThat(result).isInstanceOf(TestObjectGeneric::class.java)
        assertThat(result.t).isNull()
    }
}