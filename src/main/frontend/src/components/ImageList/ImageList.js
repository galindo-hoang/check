import React from "react";
import ImageCard from "../ImageCard/ImageCard";
import './style.css'

function ImageList(props) {
    return (
        <div className="image-list">
        {
            props.images.map(
                (image) => <ImageCard image={image} key={image.id}/>
            )
        }
        </div>
    );
}

export default ImageList;