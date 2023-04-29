const Line = (props) => {
    return (
        <tr>
            <td height={props.height}>
                {props.children}
            </td>
        </tr>
    )
}
export default Line