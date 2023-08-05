"use client"

import React, { useState } from 'react'
import Navbar from './Navbar';
import FileList from './FileList';

const Home = () => {
    const [mediaFiles, setMediaFiles] = useState<MediaFile[]>([]);

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