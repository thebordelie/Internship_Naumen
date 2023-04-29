import { useState } from "react"
import DefaultButton from "./DefaultButton"

const Table = ({requests, columnName, ...props}) => {
    const [isShowTable, setShowTable] = useState(false)

    const changeShowTable = (e)=>{
        e.preventDefault()
        setShowTable(!isShowTable)
    }

    return(
        <div className="table">
            <DefaultButton type="submit" onClick={changeShowTable} text={isShowTable? "Скрыть таблицу" : "Показать таблицу"}></DefaultButton>
            {
            isShowTable?
            <table {...props}>
                <thead>
                    <tr>
                        {columnName.map((name, index) => {
                            return (
                                <th key={index}>
                                    {name}
                                </th>
                            )
                        })}
                    </tr>
                </thead>
                <tbody>
                    {requests.map((request, index) =>{
                    return (
                        <tr key={index}>
                            <td>
                                {index+1}
                            </td>
                        <td >
                            {request.time}
                        </td>
                        </tr>
                    )
                    })}
                </tbody>
            </table>
            : 
            <div>
                {
                    requests.length==0?
                    'Запросы отсутствуют' :
                    <div> 
                        Количество запросов: {requests.length}
                    </div>
                }
                
            </div>
            }
        </div>

    )
}

export default Table