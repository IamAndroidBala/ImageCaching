# ImageCaching
<h1>Short Description</h1>
<p>This app Caching the image into external storage <br/> Here i dont use any third party like <strong>Glide</strong> or <strong>Picasso</strong> for caching images </p1>

I have used <Strong> Dagger 2 </Strong> and <Strong> Retrofit 2 </Strong> for Dependancy injection and Network API calls 

I have created a module for caching images from API. This module can be accessed simply from where in this app.

val imageLoader = ImageLoader(this)
imageLoader.displayImage(imageUrl, target, progressBar)

To cache the api response, i have created OkHttpClient with cache request, So that retrofit can cahce the response in cache

That's it.
