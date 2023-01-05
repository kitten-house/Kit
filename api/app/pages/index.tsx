import * as React from 'react'
import * as ReactDOM from 'react-dom';
import { GoogleMap } from "react-google-maps"

export const HelloComponent = () => {
    return <GoogleMap
        defaultZoom={8}
        defaultCenter={{ lat: -34.397, lng: 150.644 }}
    />;
};

ReactDOM.render(
    <HelloComponent/>,
    document.getElementById('root')
);