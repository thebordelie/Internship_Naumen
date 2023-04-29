import { useState } from "react";
import "../css/style.css"
import DefaultInput from "./UI/DefaultInput";
import { getInfo, getMaxAge, postUserName } from "../httpRequest/userHandler";
import DefaultButton from "./UI/DefaultButton";
import Table from "./UI/Table";
import Line from "./UI/LineOfTable";


function App() {
  const [userName, setUserName] = useState("")
  const [userWithMaxAge, setUserNameWithMaxAge] = useState("")
  const [userAge, setUserAge] = useState(0)
  const [userMaxAge, setUserMaxAge] = useState(-1)
  const [table, setTable] = useState([])
  const height = 140
  const changeName = (name) => {
    setUserAge(0)
    setUserName(name)
  }

  const sendName = (e) => {
    e.preventDefault()
    postUserName(userName, setUserAge)
  }

  const getInfoAboutUser = (e) => {
    e.preventDefault()
    getInfo(userName, setTable)
  }

  const getUserWithMaxAge = (e) => {
    e.preventDefault()
    getMaxAge(setUserMaxAge, setUserNameWithMaxAge)
  }

  return (
    <div className="App">
      <div className="userForm"> 
        <form>
          <table border={1} >
            <tbody>

              <Line height={height}>
                  <DefaultInput value = {userName} type = "text" onChange = {name => changeName(name.target.value) } placeholder="Введите имя пользователя"/>
                  <DefaultButton type="submit" onClick={sendName} text="Отправить"></DefaultButton>
                  <DefaultButton type="submit" onClick={getInfoAboutUser} text="Получить информацию по имени"></DefaultButton>
                  <div className="user" align="center">
                    {
                      userAge > 0?
                      <div> 
                        Возраст пользователя: {userAge}
                      </div>
                      :
                      <div>
                        {
                          userAge == -1 ?
                          <div>Неправильно введено имя</div>
                          :
                          <></>
                      
                        }
                      </div>
                    }
                    
                  </div>
              </Line>

              <Line height={height}>
                <DefaultButton type="submit" onClick={getUserWithMaxAge} text="Получить пользователя с максимальным именем"></DefaultButton>
                <div className="userMaxAge" align="center">
                  {
                    userMaxAge > 0?
                    <div> 
                      <h4>Пользователь с максимальным возрастом</h4>
                      <p>Имя пользователя: {userWithMaxAge}</p>
                      <p>Возраст пользователя: {userMaxAge}</p>
                    </div>:
                    <></>
                  }
                    
                </div>
              </Line>
            </tbody>
          </table>
        </form>
      
      </div>
      <Table className="table" border={2} align="center" columnName={["Номер запроса", "Время запроса"]} requests = {table}></Table>
    </div>
  );
}

export default App;
