import { Material } from "./Material";
import { TipoPrueba } from "./TipoPrueba";

export class Prueba {
    id!:number;
    nombre!:string;
    descripcion!:string;
    fechaCreacion!:string;
    tiempoEjecucion!:string;
    coste!:number;
    personaRealizaPrueba!:string;
    material!:Material;
    tipoPrueba!:TipoPrueba;
}