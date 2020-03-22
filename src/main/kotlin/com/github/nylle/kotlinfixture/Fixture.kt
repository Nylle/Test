package com.github.nylle.kotlinfixture

import com.github.nylle.javafixture.Configuration
import com.github.nylle.javafixture.Fixture
import com.github.nylle.javafixture.SpecimenType
import kotlin.streams.asSequence

class Fixture(configuration: Configuration = Configuration()) {
    val javaFixture = Fixture(configuration)

    /**
     * Creates a new object of the specified type, using a random constructor if available
     *
     * @param <T> the type of the object to be created
     * @return a new object of the specified type {@code T}
     */
    inline fun <reified T> construct(): T = javaFixture.construct(object : SpecimenType<T>(){})

    /**
     * Creates a new object of the specified type, recursively populated with random values
     *
     * @param <T> the type of the object to be created
     * @return a new object of the specified type {@code T}
     */
    inline fun <reified T> create(): T = javaFixture.create(object : SpecimenType<T>(){})

    /**
     * Creates a {@code Sequence} of objects of the specified type, recursively populated with random values
     *
     * @param <T> the type of the objects to be created
     * @return a {@code Sequence} of objects of the specified type {@code T}
     */
    inline fun <reified T> createMany(): Sequence<T> = javaFixture.createMany(object : SpecimenType<T>(){}).asSequence()

    /**
     * Creates a {@code Sequence} of objects of the specified type, recursively populated with random values with the specified size
     *
     * @param size the size of the {@code Sequence} to be created
     * @param <T> the type of the objects to be created
     * @return a {@code Sequence} of objects of the specified type {@code T}
     */
    inline fun <reified T> createMany(size: Int): Sequence<T> = javaFixture.build(object : SpecimenType<T>(){}).createMany(size).asSequence()

    /**
     * Adds objects of the specified type, recursively populated with random values, to the specified {@code Collection<T>}
     * The number of objects created is specified in the {@code Configuration} under {@code streamSize} (default: 3)
     *
     * @param result the collection the created objects should be added to
     * @param <T> the type of the collection and the objects to be added
     */
    inline fun <reified T> addManyTo(result: Collection<T>) = javaFixture.addManyTo(result, object : SpecimenType<T>(){})

    /**
     * Creates a {@code SpecimenBuilder<T>} to customise the object of type {@code T} to be created
     *
     * @param <T> the type of the object to be created
     * @return a builder for further customisation
     */
    inline fun <reified T> build(): SpecimenBuilder<T> = SpecimenBuilder(javaFixture.build(object : SpecimenType<T>(){}))

}