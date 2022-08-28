import {UserCredentials} from "@/types/UserCredentials";
import {JwtResponse} from "@/types/JwtResponse";


export async function sendUserLogin(credentials: UserCredentials):Promise<JwtResponse>  {
    return fetch(`/api/login`, {
    method: "POST",
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    body: encodeURI(`username=${credentials.username}&password=${credentials.password}`),
    }).then(((response) =>{
        if(response.ok) {
            return response.json().then(data => ({...data,valid:true}))
        }
        return {valid: false} as JwtResponse;
    }));
}
