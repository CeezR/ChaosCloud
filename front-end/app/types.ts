type MediaFile = {
    id : number,
    fileName : string,
    postedBy : string,
    createdDate : string,
    fileExtension : string,
    fileSize : number,
}

type MediaFileDownload = {
    fileName : string,
    fileContent : string
}