import { Client } from "./client";

export class Order {

    id: number;
    codigo: string;
    fecha: Date;
    cliente: Client;
}