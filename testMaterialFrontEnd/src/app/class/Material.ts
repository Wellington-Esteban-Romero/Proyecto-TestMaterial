import { TipoMaterial } from "./TipoMaterial";

export class Material {
    id!:number;
    descripcion!:string;
    fabricante!:string;
    fechaAdquisicion!:string;
    vidaUtil!:string;
    tipo!:TipoMaterial;
}