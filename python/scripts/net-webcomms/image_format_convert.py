#!/usr/bin/env python3

import os
from PIL import Image

img_src_path = "images"
img_dst_path = "/opt/icons/"

imgs_src = [os.path.join(img_src_path, img) for img in os.listdir(img_src_path)]
imgs_dst = [os.path.join(img_dst_path, img) for img in os.listdir(img_src_path)]

if __name__ == '__main__':
    src = None
    dst = None
    #
    for i in range(len(imgs_src)):
        src = Image.open(imgs_src[i], 'r', ['TIFF'])
        new_img = src.rotate(-90).resize((128,128))
        dst = imgs_dst[i]
        new_img.convert('RGB').save(dst, 'JPEG')