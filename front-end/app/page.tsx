import Image from 'next/image'
import React from 'react'
import SubmiteForm from './SubmiteForm'
import Navbar from './Navbar'

export default async function Home() {

  return (
    <>
      <Navbar />
      <main>
        <SubmiteForm />
      </main>
    </>
  )
}




