import { Order } from "./order";

export class Article {
    id: number;
    codigo: string;
    nombre: string;
    precioUnitario: number;
    orden: Order;
}