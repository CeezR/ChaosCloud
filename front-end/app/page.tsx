import Image from 'next/image'
import React from 'react'
import SubmiteForm from './SubmiteForm'
import Navbar from './Navbar'
import FileList from './FileList'

export default async function Home() {

  return (
    <>
      <Navbar />
      <main className='max-w-screen-xl mx-auto'>
        <FileList />
      </main>
    </>
  )
}




