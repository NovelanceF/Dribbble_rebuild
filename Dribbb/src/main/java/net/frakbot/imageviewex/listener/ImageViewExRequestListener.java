package net.frakbot.imageviewex.listener;

import com.foxykeep.datadroid.requestmanager.RequestManager.RequestListener;

import net.frakbot.imageviewex.ImageViewNext;

public abstract class ImageViewExRequestListener implements RequestListener {
	protected ImageViewNext mImageViewNext;
	
	public ImageViewExRequestListener(ImageViewNext imageViewNext) {
		this.mImageViewNext = imageViewNext;
	}
}
