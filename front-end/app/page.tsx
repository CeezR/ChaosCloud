import Image from 'next/image'

export default async function Home() {
  const data = await getData()

  return (
    <main>
      {data}
    </main>
  )
}

import React from 'react'

export async function getData() {
  const res = await fetch("http://localhost:8080/api");
  
  if (!res.ok) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error('Failed to fetch data')
  }
 
  return res.text()
}



