package edu.ucne.caomanager.data.local.entity.mapper


import edu.ucne.caomanager.data.local.entity.AnimalEntity
import edu.ucne.caomanager.domain.model.Animal


fun AnimalEntity.toDomain(): Animal {
    return Animal(
        id = this.idAnimal,
        codigo = this.codigo,
        fechaNacimiento = this.fechaNacimiento,
        pesoInicial = this.pesoInicial,
        precioCompra = this.precioCompra,
        estado = this.estado,
        pesoActual = TODO(),
    )
}

fun Animal.toEntity(): AnimalEntity {
    return AnimalEntity(
        idAnimal = this.id,
        codigo = this.codigo,
        fechaNacimiento = this.fechaNacimiento,
        pesoInicial = this.pesoInicial,
        precioCompra = this.precioCompra,
        estado = this.estado
    )
}