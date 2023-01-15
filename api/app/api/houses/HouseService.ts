import {sendApiRequest, Subscribtion} from "../../core/net/client";

export interface House {
    id: number,
    property_type: string,
    price: number,
    id_user: number,
    photo: string,
    description: string,
    house_area: number,
    room: number,
    address: Address,
    amenities: Amenities,
}

export interface Address {
    id: number,
    apartment: string
    country: string
    city: string
    house_number: string
    street: string
}

export interface Amenities {
    id: number,
    air_conditioning: boolean,
    heating: boolean,
    indoor_fireplace: boolean,
    pool: boolean,
    washing_machine: boolean,
    wi_fi: boolean,
}

interface HousesResponse {

}

export class HouseService {
    getHouses(): Subscribtion<House[]> {
        return sendApiRequest<House[]>(
            { url: "/api/houses", method: "GET" },
            (response) => { return response.data }
        )
    }
}