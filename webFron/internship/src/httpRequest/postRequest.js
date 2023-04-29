import axios from "axios"


export const httpRequest = async(httpMethod, serverURL, dataForServer) => {
    return await axios({
        method: httpMethod, 
        url: serverURL,
        data: dataForServer,
        headers:{'Content-Type': 'application/json; charset=utf-8'}
    })
}


