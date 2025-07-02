# Aplicación: PatioBalmax

Aplicación Android para la gestión de estacionamiento, desarrollada en Kotlin, con base de datos local Room, autenticación de usuarios, y carga de registros desde archivos `.txt`.

## Estructura del Proyecto

- `LoginEstacionamiento.kt`: Pantalla de inicio de sesión.
- `MapaEstacionamiento.kt`: Mapa principal del estacionamiento.
- `PatioEstacionamiento.kt`: Detalles de cada patio.
- `EditarPatioEstacionamiento.kt`: Edición de puestos.
- `AdministrarUsuarios.kt`: Gestión de usuarios y permisos.
- `database/`: Entidades, DAOs y base de datos Room.
- `adapter/`: Adaptadores para RecyclerView.
- `util/`: Funciones auxiliares y constantes.
- `model/`: Modelos de datos como ArchivoRegistro y EstadoPuesto.
- `ui/`: Actividades y navegación.
- `res/drawable`: Imágenes utilizadas.
- `res/layout`: Diseños XML de las vistas.
- `res/values`: Strings, colores, dimensiones y estilos.
- `res/raw`: Archivos iniciales de arrendatarios y particulares.

## Usuarios por defecto:

| Usuario   | Contraseña | Permisos                                      |
|-----------|------------|-----------------------------------------------|
| admin     | admin      | Administrador                                 |
| user1     | 12345      | Editar Patentes de Patios                     |
| user2     | 12345      | Validar Patios y Puestos                      |

## Requisitos mínimos:
- API 34 (Android 14)
- Kotlin DSL
