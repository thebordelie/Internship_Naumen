const DefaultButton = ({text, ...props}) => {
    return (
        <p><button {...props}>{text}</button></p>
    )
}

export default DefaultButton