export class Usuario {
    id!:number;
    username!:string;
    email!:string;
    password!:string;
    enabled!:boolean;
    nombre!:string;
    apellidos!:string;
    roles:string[] = []
}