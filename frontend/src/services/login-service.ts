import {UserCredentials} from "@/types/UserCredentials";
import {JwtResponse} from "@/types/JwtResponse";


export async function sendUserLogin(credentials: UserCredentials):Promise<JwtResponse> {
    try {
        const response = await fetch(`/api/login`, {
            method: "POST",
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: encodeURI(`username=${credentials.username}&password=${credentials.password}`),
        })
        return response.json() as Promise<JwtResponse>;
    } catch {
        console.log("bad credentials");
        return {access_token: ""} as JwtResponse;
    }

}
