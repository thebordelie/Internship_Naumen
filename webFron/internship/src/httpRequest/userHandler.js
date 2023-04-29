import { httpRequest } from "./postRequest"

const SERVER_URL = process.env.REACT_APP_SERVER_URL
const GET_AGE_URL = process.env.REACT_APP_POST_USER_NAME_URL
const GET_MAX_AGE_URL = process.env.REACT_APP_GET_MAX_AGE_UTR
const GET_INFO = process.env.REACT_APP_GET_INFO_URL

export const getMaxAge = async (setUserAge, setUserNameWithMaxAge) => {
    await httpRequest("get", SERVER_URL+GET_MAX_AGE_URL)
    .then(response=>{
        if (response.status === 204) return
        let data = JSON.parse(response.data.response)
        setUserAge(data.age)
        setUserNameWithMaxAge(data.name)
      })
}

export const postUserName = async (name, setUserAge) => {
    const data = JSON.stringify({userName: name})
    await httpRequest('post', SERVER_URL+GET_AGE_URL, data)
    .then(response=>{
        setUserAge(JSON.parse(response.data.response).age)
      })
      .catch(error=>{
          setUserAge(-1)
      })
    
}

export const getInfo = async (name, setTable) =>{
    const data = JSON.stringify({userName: name})

    await httpRequest('post', SERVER_URL+GET_INFO, data)
    .then(response=>{
        setTable([])
        if (response.status === 204) return
        let data =JSON.parse(response.data.response)
        let newDate = []
        for (let i = 0; i < data.length; i++) {
          newDate = [...newDate, {time: data[i].timeOfRequest, id: i}]
        }
        setTable(newDate)
      })
}