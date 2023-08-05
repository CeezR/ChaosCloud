"use client"

import React, { useEffect, useState } from 'react'
import Navbar from './Navbar';
import FileList from './FileList';

const Home = () => {
    const [mediaFiles, setMediaFiles] = useState<MediaFile[]>([]);

    useEffect(() => {
        getFiles();
      }, []);
    

    const getFiles = async () => {
        const response = await fetch("http://localhost:8080/api/files");
        if (!response.ok) {
          throw new Error("Failed to add job");
        }
        const data : MediaFile[] = await response.json();
        setMediaFiles(data);
      };

    return (
      <>
        <Navbar mediaFiles={mediaFiles} setMediaFiles={setMediaFiles}/>
        <main className='max-w-screen-xl mx-auto'>
            <FileList mediaFiles={mediaFiles} />
        </main>
      </>
    )
}

export default Home